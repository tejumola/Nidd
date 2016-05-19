package pinnacle.org.nidd.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import junit.framework.Assert;


import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import pinnacle.org.nidd.NiddApplication;
import pinnacle.org.nidd.R;
import pinnacle.org.nidd.common.GenericFragment;
import pinnacle.org.nidd.model.Operator;
import pinnacle.org.nidd.model.Visitor;
import pinnacle.org.nidd.utils.DrawableUtils;
import pinnacle.org.nidd.views.MainActivity;
import pinnacle.org.nidd.views.adapter.ChatRecyclerAdapter;
import rx.Observable;

/**
 * Created by DOTECH on 15/05/2016.
 */
public class ChatFragment extends GenericFragment<ChatFragment> {
    private RecyclerView mRecyclerView;
    private ChatRecyclerAdapter mAdapter;
    private final List<Visitor> data= new CopyOnWriteArrayList<>();
    private Ops userOps;

    private View.OnClickListener mClickListener= new View.OnClickListener ( ) {
        @Override
        public void onClick(View v) {
            int id=v.getId();

        }
    };


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= super.onCreateView ( inflater, container, savedInstanceState );
        initViews(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated ( view, savedInstanceState );
        view.setTag (TAG);
        mRecyclerView= (RecyclerView) view.findViewById (R.id.visitor_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext ()));
        mAdapter= new ChatRecyclerAdapter( getContext (),getData() );
        mRecyclerView.setAdapter(mAdapter);
        //mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        Toast.makeText(getContext(),mAdapter.getItemCount()+"",Toast.LENGTH_LONG)
        .show();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated ( savedInstanceState );
        Assert.assertTrue (isAdded ());
        if(!isAdded ())
            return;

    }

    public List<Visitor> getData() {
        if(data.isEmpty ()){
            addDefaultData();
        }
        return data;
    }


    /**
     * just add default data: NOte this must be replaced with an asynchronous call to retrieve the data from the server
     */
    private void addDefaultData() {
        Operator operator = new Operator.OperatorBuilder ("Alice Whiteman",null,null)
                .build();
        Visitor visitor=new Visitor(operator);

        data.add(visitor);


        //add the user avatar
        operator.setUserAvatar ( DrawableUtils.drawableToByteArray( ContextCompat.getDrawable(getActivity(), R.drawable.avatar_roy )));

        Operator operator1 = new Operator.OperatorBuilder ("#2333333667788",null,null)
                .build();
        Visitor visitor1=new Visitor(operator1);

        data.add(visitor1);


        //add the user avatar
        operator1.setUserAvatar ( DrawableUtils.drawableToByteArray( ContextCompat.getDrawable(getActivity(), R.drawable.avatar_moss )));

        Operator operator3 = new Operator.OperatorBuilder ("Kim",null,null)
                .build();
        Visitor visitor3=new Visitor(operator3);

        data.add(visitor3);


        //add the user avatar
        operator3.setUserAvatar ( DrawableUtils.drawableToByteArray( ContextCompat.getDrawable(getActivity(), R.drawable.avatar_jen )));
    }


    private void initViews(View view) {
        //set the toolbar
        ((MainActivity)getActivity ()).setToolbar ("", R.id.toolbar,true);
        Log.d ( TAG,"innitializing views");


    }

    @Override
    public void onPause() {
        super.onPause();
        //((NiddApplication)getActivity().getApplication()).setFragmentSavedState(TAG, getFragmentManager().saveFragmentInstanceState(this));
    }

    @Override
    protected int getFragmentView() {
        return R.layout.chat_fragment;
    }

    /**
     * this defines(Outlines) the major operations to be carried out by the user on the Timeline tab
     */
    private interface Ops{
        Observable<?> startCamera();
        Observable<Byte[]> takePhotoOrVideo();
       // Observable<Post>  addCaptionToData(Byte[] photoVideo);
        //Observable<Post> savePostContentToPhone(Post<User> post);
        //Observable<Collection<Post<User>>> pushAndSyncToCloud(Post<User> post);
    }
}
