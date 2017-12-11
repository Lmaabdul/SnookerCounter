package com.konieczny91.adam.snookercounter;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.konieczny91.adam.snookercounter.data.PlayerDbHelper;
import com.konieczny91.adam.snookercounter.logic.Player;
import com.konieczny91.adam.snookercounter.logic.StatisticPlayerAdapter;

import java.util.ArrayList;

public class StatisticActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Player> players;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        PlayerDbHelper database = new PlayerDbHelper(this);

        players = new ArrayList<>(database.getAllPlayers());

        listView = (ListView) findViewById(R.id.statistic_list_view);

        StatisticPlayerAdapter adapter = new StatisticPlayerAdapter(this,players);

        listView.setAdapter(adapter);

        Typeface retro = Typeface.createFromAsset(this.getAssets(),"fonts/8-BIT WONDER.ttf");

        TextView textView = (TextView) findViewById(R.id.statistic_pick_text_view);

        textView.setTypeface(retro);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                int playerId;
                playerId = players.get(position).getId();
                Toast.makeText(StatisticActivity.this, "Id is"+playerId, Toast.LENGTH_SHORT).show();
            }
        });


    }
}
