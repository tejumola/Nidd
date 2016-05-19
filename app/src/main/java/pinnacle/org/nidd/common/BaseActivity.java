package pinnacle.org.nidd.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import pinnacle.org.nidd.MVP;


/**
 * Created by DOTECH on 4/20/16.
 */
public abstract class BaseActivity extends AppCompatActivity implements  MVP.GenericViewOps {
    public static final int DURATION = 5000;
    protected CoordinatorLayout mCoordinatorLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView(getLayout());

    }

    public void showShortToast(String text){
        Toast.makeText(getBaseContext(), text, Toast.LENGTH_SHORT).show();

    }

    /**
     * show a snackbar on the coordinator layout, you could use presenter as the listener or anyother listener you like
     * @param text
     * @param actiontext
     * @param listener
     */
    protected void showLongSnackbar( String text,String actiontext,  View.OnClickListener listener){
         Snackbar snackbar = Snackbar
                .make(mCoordinatorLayout, text, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction ( actiontext,listener );
         snackbar.show();
    }

    /**
     * show a snackbar on the coordinator layout, you could use presenter as the listener or anyother listener you like
     * @param text
     * @param actiontext
     * @param listener
     */
    public void showLongSnackbar(int id, String text, String actiontext, CoordinatorLayout coordinatorLayout, View.OnClickListener listener){
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, text, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction ( actiontext,listener );
        snackbar.getView ().setId ( id);
        snackbar.show();
    }
    public void showShortSnackbar( String text){
         Snackbar snackbar = Snackbar
                .make(mCoordinatorLayout , text, Snackbar.LENGTH_SHORT);

        snackbar.show();
    }
    public void showLongToast(String text){
        Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG).show();
    }


    /**
     * Attach a {@link android.support.v4.app.Fragment} to a view, usually a
     * {@link android.view.ViewGroup}. The view is provided as resource ID, as
     * present in {@link mypackage.R.id}.
     *
     * @param fragment
     *            The Fragment to attach.
     * @param viewId
     *            The resource ID for the view to attach the fragment to, as
     *            found in R.id.
     * @param addToBackStack
     *            {@literal true} to allow the user to undo the operation with
     *            the device's back button.
     * @param The
     *            tag name to give the Fragment as it is connected to the UI
     * @param context
     *            An {@link android.support.v4.app.FragmentActivity} that hosts
     *            the fragment and the view.
     */
    public void attachFragmentToView(Fragment fragment, int viewId,
                                     boolean addToBackStack, String tag, FragmentActivity context)
    {
        FragmentManager fragMan = context.getSupportFragmentManager();
        FragmentTransaction transaction = fragMan.beginTransaction();

        if (addToBackStack)
        {
            transaction.addToBackStack(null);
        }

        transaction.add(viewId, fragment, tag);
        transaction.commit();
    }
    /**
     * set and return the toolbar
     * @param title
     * @param toolbarId
     * @param enableHome
     * @return
     */
    public Toolbar setToolbar(String title, int toolbarId, boolean enableHome) {
        Toolbar toolbar = (Toolbar) findViewById(toolbarId);
        if(toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setHomeButtonEnabled(enableHome);
            getSupportActionBar().setDisplayHomeAsUpEnabled(enableHome);
        }
        return toolbar;
    }

    /**
     * innitialize any   recyclerview, this can be moved to a generic Activity/presenter
     */
    public RecyclerView recyclerViewInnitializer(Activity context, int id, RecyclerView.Adapter<?> adapter,
                                                 RecyclerView.LayoutManager layoutManager) {
        RecyclerView recyclerView=(RecyclerView)context.findViewById(id);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        //ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration (context, R.dimen.item_offset);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        //recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(itemAnimator);
        return recyclerView;
    }


    /**
     * template method to return the layout to be used
     * @return
     */
    protected abstract int getLayout() ;

    @Override
    public FragmentActivity getActivity() {
        return (FragmentActivity) this;
    }
    /**
     * Return the Activity context.
     */
    @Override
    public Context getActivityContext() {
        return this;
    }

    /**
     * Return the Application context.
     */
    @Override
    public Context getApplicationContext() {
        return super.getApplicationContext();
    }


    @Override
    public <Type, V> void goToNextActivity(Class<? extends Activity> clazz, Type keyExtras, V valueExtras) {
        Intent intent=new Intent(getActivity(),clazz);
        if(keyExtras !=null && valueExtras!=null)
            intent.putExtra ( keyExtras.getClass ().getSimpleName (),valueExtras.toString ());
        startActivity(intent);
    }

    protected void loadPermissions(String perm,int requestCode) {
        if (ContextCompat.checkSelfPermission(this, perm) != PackageManager.PERMISSION_GRANTED) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, perm)) {
                ActivityCompat.requestPermissions(this, new String[]{perm},requestCode);
            }
        }
    }

}
