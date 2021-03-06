package edu.cnm.deepdive.games.craps;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import edu.cnm.deepdive.games.craps.Game.Roll;
import java.util.List;

public class RollAdapter extends ArrayAdapter<Roll> {

  private int resourceId;
  private Drawable[] faces;

  public RollAdapter(Context context,List<Game.Roll> rolls,
      Drawable[] faces) {
    super(context, R.layout.rolls_graphic_list_item, rolls);
    this.resourceId = R.layout.rolls_graphic_list_item;
    this.faces = faces;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    Context context = getContext();
    LayoutInflater inflater
        =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    ConstraintLayout layout = (ConstraintLayout) inflater.inflate(resourceId, null);
    Game.Roll roll = getItem(position);

    ((ImageView) layout.findViewById(R.id.die0View)).setImageDrawable(faces[roll.dice[0] -1]);
    ((ImageView) layout.findViewById(R.id.die1View)).setImageDrawable(faces[roll.dice[1] -1]);
    String outcome;
    int background;

    switch (roll.after) {
      case WIN:
        outcome = context.getString(R.string.win_message);
        background = Color.GREEN;
        break;
      case LOSE:
        outcome= context.getString(R.string.lose_message);
        background = Color.RED;
        break;

      default:
        outcome = (roll.before == Game.State.COME_OUT)
            ? context.getString(R.string.point_message)
            : "";

        background = Color.TRANSPARENT;
        break;
    }
    ((TextView) layout.findViewById(R.id.rollView)).setText(
        context.getString(R.string.roll_message, roll.dice[0] + roll.dice[1]));
    ((TextView) layout.findViewById(R.id.resultVIew)).setText(outcome);
    layout.setBackgroundColor(background);
    return layout;


  }

}
