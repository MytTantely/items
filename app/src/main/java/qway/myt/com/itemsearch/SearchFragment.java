package qway.myt.com.itemsearch;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import qway.myt.com.itemsearch.model.FruitAdapter;
import qway.myt.com.itemsearch.model.sample.ItemSample;
import qway.myt.com.itemsearch.model.sample.ItemSampleFactory;
import qway.myt.com.itemsearch.utils.RecyclerItemListener;

public class SearchFragment extends Fragment {
    private RecyclerView recList;

    private SearchView.SearchAutoComplete mSearchAutoComplete;
    private String[] fruits = {"Apple - computer", "Apple - juice", "Apple - fruit", "Apple - seeds", "Appy", "Banana", "Cherry", "Date", "Grape", "Kiwi", "Mango", "Pear"};

    private FruitAdapter mFruitAdapter;

    private BottomNavigationView navigation;

    private List shoppingList;


//    private ProgressBar progress;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        shoppingList = new ArrayList<ItemSample>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_search, container, false);
        setHasOptionsMenu(true);

//        progress = (ProgressBar) v.findViewById(R.id.progress);

        recList = (RecyclerView) v.findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(v.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        navigation = (BottomNavigationView) getActivity().findViewById(R.id.navigation);

        return v;

    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_item_list, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        mSearchAutoComplete = (SearchView.SearchAutoComplete) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        mSearchAutoComplete.setDropDownBackgroundResource(R.drawable.common_google_signin_btn_text_light_normal_background);
        mSearchAutoComplete.setDropDownAnchor(R.id.action_search);
        mSearchAutoComplete.setThreshold(0);
//        mSearchAutoComplete.setBackgroundColor(Color.BLUE);
//        mSearchAutoComplete.setTextColor(Color.GREEN);
        mSearchAutoComplete.setDropDownBackgroundResource(android.R.color.holo_blue_light);

// FIXME, missing:
        // Listen to search view item on click event.
//        searchAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, fruits);
        mSearchAutoComplete.setAdapter(adapter);
        mSearchAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String queryString = (String) parent.getItemAtPosition(position);
                mSearchAutoComplete.setText("" + queryString);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Fetch the data remotely
//                fetchBooks(query);
                // Reset SearchView
                fetchApple(); //FIXME
                searchView.clearFocus();
                searchView.setQuery("", false);
                searchView.setIconified(true);
                searchItem.collapseActionView();
                // Set activity title to search query
                // ItemListActivity.this.setTitle(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
//        return true;
    }

    private void fetchApple() {
        // Show progress bar before making network request
        // progress.setVisibility(ProgressBar.VISIBLE);

        // progress.setVisibility(ProgressBar.GONE);

        final ArrayList<ItemSample> fruits = ItemSampleFactory.getInstance().getItems();
        mFruitAdapter = new FruitAdapter(fruits);
        mFruitAdapter.notifyDataSetChanged();

        mFruitAdapter = new FruitAdapter(getFruits());
        recList.setAdapter(mFruitAdapter);  // FIXME     java.lang.NullPointerException: Attempt to invoke virtual method 'void android.support.v7.widget.RecyclerView.setAdapter(android.support.v7.widget.RecyclerView$Adapter)' on a null object reference
        // FIXME move all search view logic into Fragments.

        recList.addOnItemTouchListener(new RecyclerItemListener(SearchFragment.this.getContext(), recList,
                new RecyclerItemListener.RecyclerTouchListener() {
                    public void onClickItem(View v, int position) {

//                        getFruits().remove(position);

                        TextView label = v.findViewById(R.id.txtLabel);
                        label.setText("Added");

                        shoppingList.add(getFruits().get(position));

                        Toast toast = Toast.makeText(v.getContext(), "Shopping list: " + shoppingList.size(), Toast.LENGTH_LONG);
                        toast.show();

                        // Enable Shopping Cart menu
                        navigation.getMenu().getItem(1).setEnabled(true);
                    }

                    public void onLongClickItem(View v, int position) {
                        System.out.println("On Long Click Item interface");
                    }
                }));
        // Remove all books from the adapter
//        itemSampleAdapter.clear();
        // Load model objects into the adapter
//        for (ItemSample book : books) {
//            itemSampleAdapter.add(book); // add book through the adapter
//        }
//        itemSampleAdapter.notifyDataSetChanged();


    }

    private List<ItemSample> getFruits() {
        return ItemSampleFactory.getInstance().getItems();
    }

}
