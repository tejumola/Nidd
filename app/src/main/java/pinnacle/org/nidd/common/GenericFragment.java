package pinnacle.org.nidd.common;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;

import java.lang.reflect.ParameterizedType;

/**This is a generic Fragment that guarantees retainance of te instance on rotation.
 * Created by Genius on 4/11/16.
 */
public abstract class GenericFragment<T extends Fragment> extends Fragment {
    /**
     * Debugging tag used by the Android logger.
     */
    public final static String TAG = new Object() { }.getClass().getEnclosingClass().getSimpleName();
    private  static Fragment mThisFragment;

    public  <T> T getInstance(Bundle args) {
        T  type=null;
        if (mThisFragment == null) {
            synchronized (getClass ()) {
                if (mThisFragment == null) {
                    Class<T> tClass=(Class<T>) ((ParameterizedType) getClass()
                            .getGenericSuperclass()).getActualTypeArguments()[0];
                    type= newInstance ();
                    mThisFragment.setArguments(args);
                }
            }

        }

        return type;
    }

    public static <T> T newInstance() {
        try {
            Class<T>  currentClass = (Class<T>) new Object() { }.getClass().getEnclosingClass();
            return currentClass.newInstance();
        } catch ( Exception e) {
            e.printStackTrace ( );
        }
         T instance= (T) new Fragment();
         return  instance;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(retain ());
    }

    /**
     * retain instance by default
     * @return
     */
    public   boolean retain(){
        return true;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getFragmentView(),container,false);

    }

    /**
     * get the View for the Fragment
     * @return
     */
    protected abstract int getFragmentView();


    protected ActionBar getActionBar() {
        return ((AppCompatActivity) getActivity()).getSupportActionBar();
    }

    /**
     * innitialize any   recyclerview, this can be moved to a generic Activity/presenter
     */
    protected RecyclerView recyclerViewInnitializer(Activity context, int id, RecyclerView.Adapter<?> adapter,
                                                    RecyclerView.LayoutManager layoutManager) {
        RecyclerView recyclerView=(RecyclerView)context.findViewById(id);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize ( true );
        //ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration (context, R.dimen.item_offset);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        //recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(itemAnimator);
        return recyclerView;
    }

    public String getName(){
        return getClass().getSimpleName ();
    }

    public void switchVisibility(final View view ){
        boolean visible=view.getVisibility()==View.VISIBLE;
        TranslateAnimation translateDown= new TranslateAnimation(0,0,0,view.getY ());
        TranslateAnimation translateUp= new TranslateAnimation(0,0,view.getY (),0);
        translateDown.setDuration(300);
        translateDown.setFillAfter(true);
        translateUp.setDuration(300);
        translateUp.setFillAfter(true);
        view.setVisibility(visible ?View.INVISIBLE :View.VISIBLE);
        if(visible)
            view.startAnimation ( translateUp );
        else
             view.startAnimation ( translateDown );
    }
}
