package qway.myt.com.itemsearch;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
//import android.support.v7.app.ActionBarActivity;


import java.util.ArrayList;
import java.util.List;

import qway.myt.com.itemsearch.model.FruitAdapter;
import qway.myt.com.itemsearch.model.ItemClient;
import qway.myt.com.itemsearch.model.sample.ItemSample;
import qway.myt.com.itemsearch.model.sample.ItemSampleFactory;

public class ItemListActivity extends AppCompatActivity {

    private SearchView.SearchAutoComplete mSearchAutoComplete;
    private String[] fruits = {"Apple - computer", "Apple - juice", "Apple - fruit", "Apple - seeds", "Appy", "Banana", "Cherry", "Date", "Grape", "Kiwi", "Mango", "Pear"};

    public static final String BOOK_DETAIL_KEY = "book";

    private ListView lvBooks;
//    private ItemSampleAdapter itemSampleAdapter;
    private FruitAdapter mFruitAdapter;
    private ItemClient client;
    private ProgressBar progress;

    private RecyclerView recList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);



//        lvBooks = (ListView) findViewById(R.id.lvBooks);
//        ArrayList<ItemSample> aBooks = new ArrayList<ItemSample>();
//        itemSampleAdapter = new ItemSampleAdapter(this, aBooks);
//        lvBooks.setAdapter(itemSampleAdapter);

        progress = (ProgressBar) findViewById(R.id.progress);

        // Fetch the data remotely
        // fetchBooks("lord of the rings");

        //setupBookSelectedListener();

    }

    private List<ItemSample> getFruits() {
        return ItemSampleFactory.getInstance().getItems();
    }

    private void setupBookSelectedListener() {
        lvBooks.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Launch the detail view passing book as an extra
                Intent intent = new Intent(ItemListActivity.this, MapsActivity.class);
//                intent.putExtra(BOOK_DETAIL_KEY, itemAdapter.getItem(position));
                startActivity(intent);
            }
        });
    }

    // Executes an API call to the OpenLibrary search endpoint, parses the results
    // Converts them into an array of book objects and adds them to the adapter
//    private void fetchBooks(String query) {
//
//        // Show progress bar before making network request
//        progress.setVisibility(ProgressBar.VISIBLE);
//
//
//        client = new ItemClient();
//        client.getItems(query, new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                try {
//
//                    // hide progress bar
//                    progress.setVisibility(ProgressBar.GONE);
//
//                    JSONArray docs = null;
//                    if (response != null) {
//                        // Get the docs json array
//                        docs = response.getJSONArray("docs");
//                        // Parse json array into array of model objects
//                        final ArrayList<Item> books = Item.fromJson(docs);
//                        // Remove all books from the adapter
//                        itemSampleAdapter.clear();
//                        // Load model objects into the adapter
//                        for (ItemSample book : books) {
//                            itemSampleAdapter.add(book); // add book through the adapter
//                        }
//                        itemSampleAdapter.notifyDataSetChanged();
//                    }
//                } catch (JSONException e) {
//                    // Invalid JSON format, show appropriate error.
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                progress.setVisibility(ProgressBar.GONE);
//            }
//        });
//    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item_list, menu);
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


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fruits);
        mSearchAutoComplete.setAdapter(adapter);
        mSearchAutoComplete.setOnItemClickListener(new OnItemClickListener() {
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
                ItemListActivity.this.setTitle(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }

    private void fetchApple() {
        // Show progress bar before making network request
        progress.setVisibility(ProgressBar.VISIBLE);

        progress.setVisibility(ProgressBar.GONE);

        final ArrayList<ItemSample> fruits = ItemSampleFactory.getInstance().getItems();
        mFruitAdapter = new FruitAdapter(fruits);
        mFruitAdapter.notifyDataSetChanged();

        mFruitAdapter = new FruitAdapter(getFruits());
        recList.setAdapter(mFruitAdapter);
        // Remove all books from the adapter
//        itemSampleAdapter.clear();
        // Load model objects into the adapter
//        for (ItemSample book : books) {
//            itemSampleAdapter.add(book); // add book through the adapter
//        }
//        itemSampleAdapter.notifyDataSetChanged();


    }
}
