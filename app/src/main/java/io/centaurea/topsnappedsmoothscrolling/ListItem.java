package io.centaurea.topsnappedsmoothscrolling;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractHeaderItem;
import eu.davidea.flexibleadapter.items.AbstractSectionableItem;
import eu.davidea.viewholders.FlexibleViewHolder;

public class ListItem extends AbstractSectionableItem<ListItem.ItemViewHolder, ListHeader> {

    private final Integer itemId;
    private final boolean empty;

    public ListItem(ListHeader header, int itemId, boolean empty) {
        super(header);
        this.itemId = itemId;
        this.empty = empty;
    }

    public String getItem() {
        return itemId.toString();
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if(o instanceof ListItem){
            ListItem item = (ListItem) o;
            if (this.getHeader().getHeader().equals(item.getHeader().getHeader())){
                result = this.itemId.equals(item.itemId);
            }
        }
        return result;
    }

    @Override
    public int getLayoutRes() {
        //return !this.empty ? R.layout.list_item : R.layout.list_item_empty;
        return R.layout.list_item;
    }

    @Override
    public ItemViewHolder createViewHolder(FlexibleAdapter adapter, LayoutInflater inflater, ViewGroup parent) {
        View view = inflater.inflate(getLayoutRes(), parent, false);
        return new ItemViewHolder(view, adapter,true);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter adapter, ItemViewHolder holder, int position, List payloads) {
        holder.refreshHeader(this, position);
    }

    public static class ItemViewHolder extends FlexibleViewHolder {
        public ItemViewHolder(View view, FlexibleAdapter adapter, boolean stickyHeader) {
            super(view, adapter,stickyHeader);
        }

        public void refreshHeader(ListItem listItem, int position){
            if (!listItem.empty){
                ((TextView)itemView.findViewById(R.id.title)).setText(listItem.getItem() + " at position " + position);
            }
            else {
                ((TextView)itemView.findViewById(R.id.title)).setText("");
            }
        }
    }


}
