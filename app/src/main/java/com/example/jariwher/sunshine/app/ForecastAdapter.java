package com.example.jariwher.sunshine.app;

/**
 * Created by jariwher on 10/16/2015.
 */

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * {@link ForecastAdapter} exposes a list of weather forecasts
 * from a {@link android.database.Cursor} to a {@link android.widget.ListView}.
 */
public class ForecastAdapter extends CursorAdapter {
    private static final int VIEW_TYPE_TODAY = 0;
    private static final int VIEW_TYPE_FUTURE_DAY = 1;
    private static final int VIEW_TYPE_COUNT = 2;
    private boolean mUseTodayLayout;

    public ForecastAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    public void setUseTodayLayout(boolean useTodayLayout) {
        mUseTodayLayout = useTodayLayout;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == 0 && mUseTodayLayout) ? VIEW_TYPE_TODAY : VIEW_TYPE_FUTURE_DAY;
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE_COUNT;
    }

    /**
     * Prepare the weather high/lows for presentation.
     */
    /*private String formatHighLows(double high, double low) {
        boolean isMetric = Utility.isMetric(mContext);
        String highLowStr = Utility.formatTemperature(context, high, isMetric) + "/" + Utility.formatTemperature(context, low, isMetric);
        return highLowStr;
    }*/

    /*
        This is ported from FetchWeatherTask --- but now we go straight from the cursor to the
        string.
     */
    /*private String convertCursorRowToUXFormat(Cursor cursor) {
        // get row indices for our cursor
        String highAndLow = formatHighLows(
                cursor.getDouble(ForecastFragment.COL_WEATHER_MAX_TEMP),
                cursor.getDouble(ForecastFragment.COL_WEATHER_MIN_TEMP));

        return Utility.formatDate(cursor.getLong(ForecastFragment.COL_WEATHER_DATE)) +
                " - " + cursor.getString(ForecastFragment.COL_WEATHER_DESC) +
                " - " + highAndLow;
    }*/

    /*
        Remember that these views are reused as needed.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        int viewType = getItemViewType(cursor.getPosition());
        int layoutId = -1;
        if (viewType == VIEW_TYPE_TODAY)
            layoutId = R.layout.list_item_forecast_today;
        else
            layoutId = R.layout.list_item_forecast;
        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    /*
        This is where we fill-in the views with the contents of the cursor.
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // our view is pretty simple here --- just a text view
        // we'll keep the UI functional with a simple (and slow!) binding.
        ViewHolder viewHolder = (ViewHolder) view.getTag();
// Read weather icon ID from cursor
        int weatherId = cursor.getInt(ForecastFragment.COL_WEATHER_CONDITION_ID);
        Log.d("weatherId :", weatherId + "");
        long todaydate = cursor.getLong(ForecastFragment.COL_WEATHER_DATE);
        /*String friendlyDateText = Utility.getDayName(context, todaydate);
        String dateText = Utility.getFormattedMonthDay(context, todaydate);
        */// Use placeholder image for now
        int viewType = getItemViewType(cursor.getPosition());
        int fallbackIconId;
        switch (viewType) {
            case VIEW_TYPE_TODAY: {
                // Get weather icon
                fallbackIconId = Utility.getArtResourceForWeatherCondition(
                        weatherId);
           //     viewHolder.dateView.setText(friendlyDateText + "," + dateText);
                break;
            }
            case VIEW_TYPE_FUTURE_DAY: {
                // Get weather icon
                fallbackIconId = Utility.getIconResourceForWeatherCondition(
                        weatherId);
                //  viewHolder.iconView.setImageResource(Utility.getIconResourceForWeatherCondition(weatherId));
             //   viewHolder.dateView.setText(friendlyDateText);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown view type: " + viewType);
        }

        Glide.with(mContext)
                .load(Utility.getArtUrlForWeatherCondition(mContext, weatherId))
                .error(fallbackIconId)
                .crossFade()
                .into(viewHolder.iconView);
        // Find TextView and set formatted date on it
        viewHolder.dateView.setText(Utility.getFriendlyDayString(context, todaydate));

        // TODO Read date from cursor


        // TODO Read weather forecast from cursor
        //   String description = cursor.getString(ForecastFragment.COL_WEATHER_DESC);
        String description = Utility.getStringForWeatherCondition(context, weatherId);
        // Read Text View and set Weather forecast on it

        viewHolder.descriptionView.setText(description);
        viewHolder.descriptionView.setContentDescription(context.getString(R.string.a11y_forecast, description));


        // Read high temperature from cursor
        String high = Utility.formatTemperature(context, cursor.getDouble(ForecastFragment.COL_WEATHER_MAX_TEMP));


        viewHolder.highTempView.setText(high);
        viewHolder.highTempView.setContentDescription(context.getString(R.string.a11y_high_temp, high));
        // TODO Read low temperature from cursor
        String low = Utility.formatTemperature(context, cursor.getDouble(ForecastFragment.COL_WEATHER_MIN_TEMP));
        viewHolder.lowTempView.setText(low);
        viewHolder.lowTempView.setContentDescription(context.getString(R.string.a11y_low_temp, low));
    }

    /**
     * Cache of the children views for a forecast list item.
     */
    public static class ViewHolder {
        public final ImageView iconView;
        public final TextView dateView;
        public final TextView descriptionView;
        public final TextView highTempView;
        public final TextView lowTempView;

        public ViewHolder(View view) {
            iconView = (ImageView) view.findViewById(R.id.list_item_icon);
            dateView = (TextView) view.findViewById(R.id.list_item_date_textview);
            descriptionView = (TextView) view.findViewById(R.id.list_item_forecast_textview);
            highTempView = (TextView) view.findViewById(R.id.list_item_high_textview);
            lowTempView = (TextView) view.findViewById(R.id.list_item_low_textview);
        }
    }
}