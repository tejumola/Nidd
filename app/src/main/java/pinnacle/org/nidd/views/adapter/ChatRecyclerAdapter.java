package pinnacle.org.nidd.views.adapter;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import pinnacle.org.nidd.R;
import pinnacle.org.nidd.common.BaseActivity;
import pinnacle.org.nidd.fragment.ChatFragment;
import pinnacle.org.nidd.model.Visitor;
import pinnacle.org.nidd.views.SignInActivity;

/**
 * Created by DOTECH on 28/04/2016.
 */
public class ChatRecyclerAdapter extends RecyclerView.Adapter<ChatItemViewHolder>{
    private List<Visitor> visitors;
    private Context context;

    public ChatRecyclerAdapter(Context context,List<Visitor> itemsData) {
        this.visitors = itemsData;
        this.context=context;
    }

    @Override
    public ChatItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate( R.layout.chats_list, null);
        ChatItemViewHolder viewHolder= new ChatItemViewHolder (itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ChatItemViewHolder holder, int position) {
        holder.setItem(getItem(position));
    }

    private Visitor getItem(int position) throws NullPointerException,IndexOutOfBoundsException {
        if(visitors==null || visitors.isEmpty ())
            throw new NullPointerException(" there are no visitors");
        if(position< 0 || position>=visitors.size ())
            throw new IndexOutOfBoundsException ( "Invalid index" );
        return visitors.get ( position );
    }


    @Override
    public int getItemCount() {
        return visitors.size();
    }
}
