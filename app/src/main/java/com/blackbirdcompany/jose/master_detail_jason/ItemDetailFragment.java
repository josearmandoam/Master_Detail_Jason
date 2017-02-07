package com.blackbirdcompany.jose.master_detail_jason;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.blackbirdcompany.jose.master_detail_jason.dummy.DummyContent;
import com.bumptech.glide.Glide;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.nom);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmento_detalle_articulo, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar_detalle);
//            toolbar.setTitle("");
           if (toolbar != null)
                toolbar.inflateMenu(R.menu.menu);

            ((TextView) v.findViewById(R.id.titulo)).setText(mItem.nom);
            ((TextView) v.findViewById(R.id.fecha)).setText(mItem.fecest);
            ((TextView) v.findViewById(R.id.contenido)).setText(mItem.info);
            //((TextView) v.findViewById(R.id.contenido)).setText(getText(R.string.in));
            Glide.with(this)
                    .load(mItem.url2)
                    .into((ImageView) v.findViewById(R.id.imagen));
        }
        return v;
    }
}
