package pinnacle.org.nidd;

import android.view.View;

import pinnacle.org.nidd.common.ContextView;
import pinnacle.org.nidd.common.ModelOps;
import pinnacle.org.nidd.common.PresenterOps;
import pinnacle.org.nidd.model.Operator;

/**
 * Created by DOTECH on 28/04/2016.
 * This class defines the interface for Nidd Client application
 * that are required and provided by the layers in the
 * Model-View-Presenter (MVP) pattern.This design ensures loose
 * coupling between the layers in the app's MVP-based architecture
 *
 *
 * The aim of this class is to insulate you from the underlying implementation and make it clean for you to work and handle
 * configuration change and other issues.
 */
public interface MVP {
    /**
     * generic model that any model class can implement to be able to get presenter back and forth
     *
     */
    interface GenericNiddModelsOps extends ModelOps<GenericNiddPresenter> {
        /**
         * this defines the callback response to be carried out on the presenter layer
         */
        interface ResponseOPsCallback {
            void onSuccess(Operator operator);
            void onFailed(Throwable cause);
        }

    }

    /**
     * this defines the Generic Operations to be performed on View Layer on the MVP pattern
     */
    interface GenericViewOps<T extends GenericNiddPresenter> extends ContextView {
        <T> T getPresenter();
    }

    /**
     * this defines the Generic Operations to be performed by any presenter on Nidd app based on the MVP pattern

     * @param <M> any required model class
     * @param <V> the required view
     */
    interface GenericNiddPresenter<M,V extends GenericViewOps> extends PresenterOps<V>,View.OnClickListener{
        V getView();
        M getModel();
        final String TAG = new Object() {}.getClass().getEnclosingClass().getSimpleName();
    }

}
