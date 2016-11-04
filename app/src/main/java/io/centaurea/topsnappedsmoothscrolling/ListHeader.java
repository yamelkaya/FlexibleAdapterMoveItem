package io.centaurea.topsnappedsmoothscrolling;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractHeaderItem;
import eu.davidea.viewholders.FlexibleViewHolder;

public class ListHeader extends AbstractHeaderItem<ListHeader.HeaderViewHolder> {

    private final Integer headerId;
    private final boolean empty;
    private int position;

    public ListHeader(int headerId, boolean empty) {
        this.headerId = headerId;
        this.empty = empty;
    }

    public String getHeader() {
        return headerId.toString();
    }

    public boolean isFirst(){return position == 0;}

    @Override
    public boolean equals(Object o) {
        if(o instanceof ListHeader){
            return this.headerId.equals(((ListHeader) o).headerId);
        }
        return false;
    }

    @Override
    public int getLayoutRes() {
        return this.empty || isFirst() ? R.layout.list_item_empty : R.layout.list_header;
    }

    @Override
    public HeaderViewHolder createViewHolder(FlexibleAdapter adapter, LayoutInflater inflater, ViewGroup parent) {
        View view = inflater.inflate(getLayoutRes(), parent, false);
        return new HeaderViewHolder(view, adapter,true);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter adapter, HeaderViewHolder holder, int position, List payloads) {
        this.position = position;
        holder.refreshHeader(this, position);
    }

    public static class HeaderViewHolder extends FlexibleViewHolder {
        public HeaderViewHolder(View view, FlexibleAdapter adapter, boolean stickyHeader) {
            super(view, adapter,stickyHeader);
        }

        public void refreshHeader(ListHeader headerVM, int position){
            if (!headerVM.empty){
                ((TextView)itemView.findViewById(R.id.title)).setText(headerVM.getHeader() + " at position " + position);
            }
        }
    }


}
