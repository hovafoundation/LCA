package com.livecoinalert.lca.data.model;

import com.dreampany.framework.data.enums.Result;
import com.dreampany.framework.data.enums.TaskType;
import com.dreampany.framework.data.model.BaseSerial;
import com.dreampany.framework.data.model.Category;
import com.dreampany.framework.data.model.Search;
import com.dreampany.framework.data.model.Task;
import com.livecoinalert.lca.data.enums.ItemState;
import com.livecoinalert.lca.data.enums.ItemSubtype;
import com.livecoinalert.lca.data.enums.ItemType;

/**
 * Created by air on 11/15/17.
 */

public class ItemTask<T extends BaseSerial> extends Task<T, ItemType, ItemSubtype, ItemState> {

    private static final int defaultLimit = 1;

    private int limit;
    private Search search;
    private Category category;

    public ItemTask() {
        this(null);
    }

    public ItemTask(T item) {
        this(item, null);
    }

    public ItemTask(T item, ItemType itemType) {
        this(item, itemType, null);
    }

    public ItemTask(T item, ItemType type, ItemSubtype subtype) {
        this(item, type, subtype, null);
    }

    public ItemTask(T item, ItemType type, ItemSubtype subtype, TaskType taskType) {
        super();
        this.item = item;
        this.type = type;
        this.subtype = subtype;
        this.taskType = taskType;
        limit = defaultLimit;
        result = Result.SUCCESS;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setSearch(Search search) {
        this.search = search;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getLimit() {
        return limit;
    }

    public Search getSearch() {
        return search;
    }

    public Category getCategory() {
        return category;
    }

    //special public api
    public boolean isFully() {
        return fully;
    }

    public ItemTask<T> copy() {
        ItemTask<T> task = new ItemTask<T>(item, type, subtype, taskType);
        task.copy(this);
        task.setLimit(limit);
        return task;
    }
}
