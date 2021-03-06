package uk.ac.prco.plymouth.thecentrecircle.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.marshalchen.ultimaterecyclerview.expanx.SmartItem;
import com.marshalchen.ultimaterecyclerview.expanx.Util.DataUtil;
import com.marshalchen.ultimaterecyclerview.expanx.customizedAdapter;

import java.util.ArrayList;
import java.util.List;

import uk.ac.prco.plymouth.thecentrecircle.R;
import uk.ac.prco.plymouth.thecentrecircle.classes.StatisticCategory;
import uk.ac.prco.plymouth.thecentrecircle.classes.StatisticSubCategory;

/**
 * Created by charliewaite on 19/04/2016.
 */
public class StatisticExpansionAdapter extends customizedAdapter<StatisticCategory, StatisticSubCategory> {


    public StatisticExpansionAdapter(Context context) {
        super(context);
    }

    public static List<SmartItem> getPreCodeMenu(String[] a, String[] b, String[] c, String[] d,
                                                 String[] e, String[] f) {
        List<SmartItem> smartItemList = new ArrayList<>();
        smartItemList.add(SmartItem.parent("Wins", "open", DataUtil.getSmallList(a)));
        smartItemList.add(SmartItem.parent("Draws", "open", DataUtil.getSmallList(b)));
        smartItemList.add(SmartItem.parent("Losses", "open", DataUtil.getSmallList(c)));
        smartItemList.add(SmartItem.parent("Goals scored", "open", DataUtil.getSmallList(d)));
        smartItemList.add(SmartItem.parent("Goals conceded", "open", DataUtil.getSmallList(e)));
        smartItemList.add(SmartItem.parent("Goals by minute", "open", DataUtil.getSmallList(f)));

        return smartItemList;
    }

    @Override
    protected StatisticCategory iniCustomParentHolder(View parentview) {
        return new StatisticCategory(parentview);
    }

    @Override
    protected StatisticSubCategory iniCustomChildHolder(View childview) {
        return new StatisticSubCategory(childview);
    }

    @Override
    protected int getLayoutResParent() {
        return R.layout.statistic_exp_parent;
    }

    @Override
    protected int getLayoutResChild() {
        return R.layout.statistic_exp_child;
    }

    @Override
    protected List<SmartItem> getChildrenByPath(String path, int depth, int position) {
        return null;
    }

    @Override
    public RecyclerView.ViewHolder newFooterHolder(View view) {
        return null;
    }

    @Override
    public RecyclerView.ViewHolder newHeaderHolder(View view) {
        return null;
    }
}
