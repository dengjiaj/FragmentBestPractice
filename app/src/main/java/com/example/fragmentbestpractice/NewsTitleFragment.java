package com.example.fragmentbestpractice;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/10.
 */
public class NewsTitleFragment extends Fragment implements AdapterView.OnItemClickListener {
    private ListView newsTitleListView;
    private List<News> newsList;
    private NewsAdapter adapter;
    private boolean isTwoPane;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        newsList = getNews();//初始化新闻内容
        adapter = new NewsAdapter(context,R.layout.news_item,newsList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_title_frag,container,false);
        newsTitleListView = (ListView) view.findViewById(R.id.news_title_list_view);
        newsTitleListView.setAdapter(adapter);
        newsTitleListView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        if(getActivity().findViewById(R.id.news_content_layout) != null){
            isTwoPane = true;//可以找到news_content_layout布局是，为双页模式
        }
        else {
            isTwoPane = false;//找不到news_content_layout布局是，为单页模式
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        News news = newsList.get(position);
        if(isTwoPane){
            //如果是双页模式，则刷新NewsContentFragment中的内容
            newsContentFragment newsContentFragment = (newsContentFragment)getFragmentManager().findFragmentById(R.id.news_content_fragment);
            newsContentFragment.refresh(news.getTitle(),news.getContent());
        }else {
            //如果是单页模式，则直接启动NewsContentActivitty
            NewsContentActivity.actionStart(getActivity(),news.getTitle(),news.getContent());
        }
    }
    private  List<News> getNews(){
        List<News> newsList = new ArrayList<News>();
        News news1 = new News();
        news1.setTitle("李克强为何百忙中在人民大会堂接待这两位外宾？");
        news1.setContent("这是李克强总理外事活动中极少出现的一幕场景：宽敞的人民大会堂福建厅只来了世界技能组织主席和CEO两位外宾，中方这边总理则带了三位相关部长。\n" +
                "4月7日下午，李克强在会见挪威首相之前特意安排近半小时时间，会见来华对上海申办2021年第46届世界技能大赛进行考察评估的世界技能组织主席巴特利等两人。\n" +
                "“国际技能大赛堪称‘世界技能的奥林匹克’。”李克强在开场白中表示。\n" +
                "“我想，除了国际奥委会，我想其他人应该都认同您这句话。”翻译刚翻译到总理的上述表示后，巴特利立马回应道。\n" +
                "“我下次见到国际奥委会的负责人时会跟他们说的!”李克强总理的回应，让会场一下变得活跃起来，“人类不仅在体育方面需要奥林匹克竞赛，在技能方面同样需要这样的竞赛。这样我们不仅可以欣赏高水平的体育运动，还能欣赏到花样百出的优秀技能竞赛。人类的各项技能空前得到提高，才能为消费者提供更多更好的商品和服务产品。”");
        newsList.add(news1);
        News news2 = new News();
        news2.setTitle("中国海军救出外籍船 专家：军力增长是福音");
        news2.setContent("【环球时报记者 郭媛丹】中国军方9日发布消息称，中国海军当天在亚丁湾营救1艘遭劫持的外籍货船。这是中国海军首次在亚丁湾、索马里海域营救遭海盗登船袭击的外籍船只。\n" +
                "　　遭海盗登船袭击的是图瓦卢籍OS35号货船，当时该船在亚丁湾索科特拉岛西北海域航行，遭遇不明数量海盗劫持，1艘海盗小艇靠泊货船。9日凌晨，正在亚丁湾、索马里海域执行护航任务的中国第25批护航编队对该货船实施成功救援。16名中国特战队员乘小艇登临该船，将19名船员解救出安全舱。\n" +
                "　　根据中国海军的消息，截至4月9日，中国海军护航编队在亚丁湾、索马里海域营救遭海盗登船袭击船舶2艘。公开报道显示，另一次营救遭海盗登船袭击船舶事件为“成功营救遭海盗登船袭击的我国商船‘泰安口’”。这次营救事件发生在2010年11月。当时，中国籍特种运输船“泰安口”轮遭1艘海盗船袭击，4名海盗已登船，商船船员情况不明。中国军舰徐州舰赶赴事发海域对被劫持船只成功实施救援。\n" +
                "　　一名军方人士9日对《环球时报》记者说，最新的货船遭劫事件表明亚丁湾仍不安全，海盗对该海域的威胁始终存在。在该海域执行反海盗的各国军舰仍面临海盗的挑衅，保护过往船舶和人员的生命和财产安全的任务依然艰巨。海军军事学术研究所研究员张军社说，中国海军成功营救外籍货船说明随着中国军力增长，中国军队为国际社会提供公共安全产品能力不断增强，对维护地区和世界和平稳定是一个福音，而不是威胁。2008年中国海军派出首批护航部队至今共完成6000余艘船舶护航任务，其中护航外国和世界粮食计划署等国际组织船舶过半，这说明中国承担了越来越多的国际责任和义务。");
        newsList.add(news2);
        return newsList;
    }
}
