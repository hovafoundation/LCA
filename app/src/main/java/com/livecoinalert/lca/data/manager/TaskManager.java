package com.livecoinalert.lca.data.manager;

import android.content.Context;

import com.livecoinalert.lca.app.App;
import com.livecoinalert.lca.data.enums.ItemSubtype;
import com.livecoinalert.lca.data.enums.ItemType;
import com.livecoinalert.lca.data.model.Demo;
import com.livecoinalert.lca.data.model.DemoItem;
import com.livecoinalert.lca.data.model.ItemTask;
import com.dreampany.framework.data.api.network.data.manager.NetworkManager;
import com.dreampany.framework.data.enums.TaskType;
import com.dreampany.framework.data.manager.AdManager;
import com.dreampany.framework.data.manager.EventManager;
import com.dreampany.framework.data.model.Progress;
import com.dreampany.framework.data.util.AndroidUtil;
import com.dreampany.framework.data.util.DataUtil;
import com.dreampany.framework.data.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by air on 10/17/17.
 */

public final class TaskManager extends EventManager<ItemTask<?>> {

    public static final int MAX_PROGRESS = 100;
    private static final int WORD_RESOLVE_COUNT = 10;

    private static final long INTERSTITIAL_EXPIRE = TimeUtil.minuteToMilli(10);
    private static final long REWARDED_EXPIRE = TimeUtil.minuteToMilli(20);
    private static final long INTERSTITIAL_COUNT = 10;
    private static final long REWARDED_COUNT = 20;

    private static TaskManager instance;
    private final Context context;
    private final NetworkManager network;

    private Processor processor;
    private AdManager ad;

    private volatile Progress loadProgress;
    private volatile Executor executor;

    private volatile long lastTime;

    private TaskManager(Context context) {
        if (context == null) {
            throw new NullPointerException();
        }
        this.context = context.getApplicationContext();
        network = new NetworkManager();
        ad = AdManager.onInstance(context);
        processor = new Processor(context, network);
        executor = Executors.newCachedThreadPool();
    }

    synchronized public static TaskManager onInstance(Context context) {
        if (instance == null) {
            instance = new TaskManager(context);
        }
        return instance;
    }

    @Override
    public void start() {
        super.start();
        network.networkChecker(context);
        loadData();
        AndroidUtil.post(() -> loopingTask());
    }

    @Override
    public void stop() {
        network.stopChecker();
        super.stop();
    }

    @Override
    protected boolean looping() throws InterruptedException {

        ItemTask<?> task = takeTask();

        if (task != null) {
            TaskType taskType = task.getTaskType();
            switch (taskType) {
                case DISCOVER:
                    putTaskUniquely(task);

/*                    if (!processor.hasDiscovery() || !hasInternet()) {
                        waitRunner(superWait);
                        return true;
                    }*/

/*                    Word word = processor.getRawWord();
                    if (word == null) {
                        word = processor.getFullWord();
                    }

                    //LogKit.verbose("Auto Discover: " + word.getWord());

                    if (word == null) {
                        waitRunner(wait);
                        wait += defaultWait;
                        return true;
                    }
                    wait = defaultWait;

                    boolean resolved = processor.resolve(word, true, WORD_RESOLVE_COUNT);
                    if (resolved) {
                        processor.publish(word);
                        autoDiscover(word);
                    }*/
                    waitRunner(mediumWait);
                    break;
            }
        }

        return true;
    }

    public void clearAllTask() {
        clearAll();
    }

    public boolean hasInternet() {
        return network.hasInternet();
    }

    private void loadData() {
        //executor.execute(this::loadRawWords);
        //executor.execute(this::loadCategories);
        //executor.execute(this::buildWordItems);
    }

    private void loopingTask() {
        ItemTask<Demo> task = new ItemTask<>(null, ItemType.DEMO, ItemSubtype.ORIGINAL, TaskType.DISCOVER);
        putTaskUniquely(task);
    }

    public void produceWords(int limit, SingleObserver<List<DemoItem>> subscriber) {
        Single<List<DemoItem>> wordsSingle = Single.fromCallable(() -> {
            List<Demo> demos = null;//processor.manager().database().wordDao().getWords(limit);
            if (!DataUtil.isEmpty(demos)) {
                List<DemoItem> items = new ArrayList<>(demos.size());
                for (int index = 0; index < demos.size(); index++) {
                    items.add(new DemoItem(demos.get(index)));
                }
                return items;
            }
            return null;
        });
        wordsSingle
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void resolve(DemoItem item) {
        executor.execute(() -> {
           // processor.resolve(item.getWord(), true, WORD_RESOLVE_COUNT);
           // processor.resolveOthers(item, item.getWord(), true);
            post(item);
        });
    }

    public void toggle(DemoItem item) {
        executor.execute(() -> {
           /* Flag flag = new Flag(item.getWord().getWord(), ItemType.WORD.value(), ItemSubtype.ORIGINAL.value());
            boolean flagged = processor.toggle(flag);
            item.setFlag(flagged);
            item.setSubtype(ItemSubtype.FLAG);
            post(item);*/
        });
    }

    ///////// Private Api
    private void progress(int current, int total, Progress progress) {
        float ratio = (float) current / total;
        int percent = (int) (ratio * MAX_PROGRESS);
        if (progress.getProgress() < percent) {
            progress.setProgress(percent);
            progress.setCount(current);
            post(progress);
        }
    }

    private void notifyProgress(ItemTask<?> task) {
        if (!task.isProgressive()) {
            return;
        }

        ItemType type = task.getType();
        ItemSubtype subtype = task.getSubtype();

        if (type == ItemType.DEMO) {
            switch (subtype) {
                case ORIGINAL:
                    //Word word = (Word) task.getItem();
                    //NotifyManager.onInstance().postProgress(word.getWord(), "Loading...", "");
                    break;
            }
        }
    }

    private void notifyService(ItemTask<?> task) {
        if (task.isError() || App.isVisible() || !processor.pref().isNotification()) {
            return;
        }

        ItemType type = task.getType();
        ItemSubtype subtype = task.getSubtype();

        /*if (type == ItemType.WORD) {
            if (subtype == ItemSubtype.TODAY || subtype == ItemSubtype.RANDOM) {
                Word word = (Word) task.getItem();
                if (!processor.isViewed(word.getWord(), type, ItemSubtype.ORIGINAL)) {
                    NotifyManager.onInstance().showNotification(context, word.getWord(), "New word", NavigationActivity.class);
                }
            }
            return;
        }*/

/*        if (type == ItemType.QUOTE) {
            Quote quote = (Quote) task.getItem();
            if (!processor.isViewed(quote.getId(), type, ItemSubtype.ORIGINAL)) {
                NotifyManager.onInstance().showNotification(context, quote.getText(), "Learn words from new quotation", NavigationActivity.class);
            }
            return;
        }*/
    }
}