package com.backtory.android.sdksample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.backtory.androidsdk.model.BacktoryLeaderBoard.LeaderBoardRank;
import com.backtory.androidsdk.model.BacktoryLeaderBoard.LeaderBoardResponse;

import java.util.Random;

/**
 * Created by Alireza Farahani on 6/24/2016.
 */
public class GameFragment extends MainActivity.AbsFragment implements View.OnClickListener {
  TextView coinView;
  TextView timeView;
  private int coinValue;
  private int timeValue;

  @Override
  protected int getLayoutRes() {
    return R.layout.fragment_game;
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View v = super.onCreateView(inflater, container, savedInstanceState);
    coinView = (TextView) v.findViewById(R.id.textview_coin);
    timeView = ((TextView) v.findViewById(R.id.textview_time));
    return v;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    refreshTimeCoin();
  }

  void sendEvent() {
    new GameOverEvent(random.nextInt(100), random.nextInt(200)).sendAsync(this.<Void>printCallBack());

    /*new GameOverEvent(random.nextInt(100), random.nextInt(200)).sendAsync(new BacktoryCallBack<Void>() {
      @Override
      public void onResponse(BacktoryResponse<Void> response) {
        if (response.isSuccessful())
          Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
        else {
          // do something based on error code
        }
      }
    });*/

    /*List<FieldValue> fieldValues = Arrays.asList(
        new FieldValue("Coin", 100), new FieldValue("Time", 200));
    BacktoryEvent event = new BacktoryEvent();
    event.setName("GameOver");
    event.setFieldsAndValues(fieldValues);
    event.sendAsync(new BacktoryCallBack<Void>() {
      @Override
      public void onResponse(BacktoryResponse<Void> response) {
        if (response.isSuccessful())
          Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
        else {
          // do something based on error code
        }
      }
    });*/

    refreshTimeCoin();
  }

  void getPlayerRank() {
    new TopPlayersLeaderBoard().getPlayerRankInBackground(this.<LeaderBoardRank>printCallBack());
    /*new TopPlayersLeaderBoard().getPlayerRankInBackground(new BacktoryCallBack<LeaderBoardRank>() {
      @Override
      public void onResponse(BacktoryResponse<LeaderBoardRank> response) {
        String leaderboardPosition = "my rank: " + response.body().getRank() + "\n my scores: " + response.body().getScores();
        if (response.isSuccessful())
          Toast.makeText(getContext(), leaderboardPosition, Toast.LENGTH_SHORT).show();
        else {
          // do something based on error code
        }
      }
    });*/
  }

  void getTopPlayers() {
    new TopPlayersLeaderBoard().getTopPlayersInBackground(2, this.<LeaderBoardResponse>printCallBack());
    /*new TopPlayersLeaderBoard().getTopPlayersInBackground(2, new BacktoryCallBack<LeaderBoardResponse>() {
      @Override
      public void onResponse(BacktoryResponse<LeaderBoardResponse> response) {
        if (response.isSuccessful()) {
          UserProfile topPlayer = response.body().getUsersProfile().get(0);
          textView.setText("best player is: " + topPlayer.getUserBriefProfile().getUserName()
              + "\n scoring: " + topPlayer.getScores());
        } else {
          // do something based on error code
        }
      }
    });*/
  }

  void getAroundMePlayers() {
    new TopPlayersLeaderBoard().getPlayersAroundMeInBackground(2, this.<LeaderBoardResponse>printCallBack());
    /*new TopPlayersLeaderBoard().getPlayersAroundMeInBackground(3, new BacktoryCallBack<LeaderBoardResponse>() {
          @Override
          public void onResponse(BacktoryResponse<LeaderBoardResponse> response) {
            if (response.isSuccessful()) {
              UserProfile aboveMePlayer = response.body().getUsersProfile().get(0);
              UserProfile belowMePlayer = response.body().getUsersProfile().get(2);

              textView.setText("you are behind " + aboveMePlayer.getUserBriefProfile().getUserName()
                  + "\n but better than " + belowMePlayer.getUserBriefProfile().getUserName();
            } else {
              // do something based on error code
            }
          }
        });*/
  }

  private static Random random = new Random(System.currentTimeMillis());

  private void refreshTimeCoin() {
    coinValue = random.nextInt(100);
    timeValue = random.nextInt(200);
    coinView.setText(String.valueOf(coinValue));
    timeView.setText(String.valueOf(timeValue));
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.button_send_event:
        sendEvent();
        break;
      case R.id.button_leader_board_rank:
        getPlayerRank();
        break;
      case R.id.button_leader_board_tops:
        getTopPlayers();
        break;
      case R.id.button_leader_board_around_me:
        getAroundMePlayers();
        break;
    }
  }
}