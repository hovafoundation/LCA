package com.livecoinalert.lca.data.service;

import com.livecoinalert.lca.app.App;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import com.oasisfeng.condom.CondomContext;

public class NotifyService extends JobService {

    public void completeJob(final JobParameters params) {
        try {
            CondomContext context = App.getContext();
//            TaskManager.onInstance(context).produceItem( ItemType.WORD, ItemSubtype.TODAY);
//            TaskManager.onInstance(context).produceItem( ItemType.QUOTE, ItemSubtype.ORIGINAL);
        } finally {
            jobFinished(params, false);
        }
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        new Thread(() -> completeJob(params)).start();
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        return false;
    }
}