package com.cs.news1.entry;

import java.util.List;

/**
 * Created by chenshuai on 2016/11/8.
 */

public class Photo{

    /**
     * error : false
     * results : [{"_id":"581fc2c0421aa91369f959f9","createdAt":"2016-11-07T07:54:40.74Z","desc":"11-7","publishedAt":"2016-11-07T11:47:36.373Z","source":"chrome","type":"福利","url":"http://ww2.sinaimg.cn/large/610dc034jw1f9j7nvnwjdj20u00k0jsl.jpg","used":true,"who":"daimajia"},{"_id":"581bd560421aa91376974628","createdAt":"2016-11-04T08:25:04.30Z","desc":"11-4","publishedAt":"2016-11-04T11:48:56.654Z","source":"chrome","type":"福利","url":"http://ww4.sinaimg.cn/large/610dc034jw1f9frojtu31j20u00u0go9.jpg","used":true,"who":"daimajia"},{"_id":"581a838a421aa90e799ec261","createdAt":"2016-11-03T08:23:38.560Z","desc":"11-3","publishedAt":"2016-11-03T11:48:43.342Z","source":"chrome","type":"福利","url":"http://ww3.sinaimg.cn/large/610dc034jw1f9em0sj3yvj20u00w4acj.jpg","used":true,"who":"daimajia"},{"_id":"58193781421aa90e6f21b49f","createdAt":"2016-11-02T08:46:57.726Z","desc":"11-2","publishedAt":"2016-11-02T11:49:08.835Z","source":"chrome","type":"福利","url":"http://ww4.sinaimg.cn/large/610dc034jw1f9dh2ohx2vj20u011hn0r.jpg","used":true,"who":"daimajia"},{"_id":"5817e1fa421aa913769745fe","createdAt":"2016-11-01T08:29:46.640Z","desc":"11-1","publishedAt":"2016-11-01T11:46:01.617Z","source":"chrome","type":"福利","url":"http://ww1.sinaimg.cn/large/610dc034jw1f9cayjaa96j20u011hqbs.jpg","used":true,"who":"daimajia"},{"_id":"5816871a421aa91369f959b6","createdAt":"2016-10-31T07:49:46.592Z","desc":"10-31","publishedAt":"2016-10-31T11:43:44.770Z","source":"chrome","type":"福利","url":"http://ww2.sinaimg.cn/large/610dc034jw1f9b46kpoeoj20ku0kuwhc.jpg","used":true,"who":"daimajia"},{"_id":"581218e9421aa90e799ec222","createdAt":"2016-10-27T23:10:33.618Z","desc":"10-28","publishedAt":"2016-10-28T11:29:36.510Z","source":"chrome","type":"福利","url":"http://ww2.sinaimg.cn/large/610dc034jw1f978bh1cerj20u00u0767.jpg","used":true,"who":"daimajia"},{"_id":"5811596a421aa90e6f21b45e","createdAt":"2016-10-27T09:33:30.47Z","desc":"10-27","publishedAt":"2016-10-27T11:41:45.88Z","source":"chrome","type":"福利","url":"http://ww4.sinaimg.cn/large/610dc034gw1f96kp6faayj20u00jywg9.jpg","used":true,"who":"daimajia"},{"_id":"58101f83421aa90e6f21b44b","createdAt":"2016-10-26T11:14:11.143Z","desc":"10-26","publishedAt":"2016-10-26T11:28:10.759Z","source":"chrome","type":"福利","url":"http://ww4.sinaimg.cn/large/610dc034jw1f95hzq3p4rj20u011htbm.jpg","used":true,"who":"daimajia"},{"_id":"580e9c74421aa90e799ec1fa","createdAt":"2016-10-25T07:42:44.254Z","desc":"10-25","publishedAt":"2016-10-25T11:35:01.586Z","source":"chrome","type":"福利","url":"http://ww4.sinaimg.cn/large/610dc034jw1f9469eoojtj20u011hdjy.jpg","used":true,"who":"daimajia"}]
     */

    private boolean error;
    /**
     * _id : 581fc2c0421aa91369f959f9
     * createdAt : 2016-11-07T07:54:40.74Z
     * desc : 11-7
     * publishedAt : 2016-11-07T11:47:36.373Z
     * source : chrome
     * type : 福利
     * url : http://ww2.sinaimg.cn/large/610dc034jw1f9j7nvnwjdj20u00k0jsl.jpg
     * used : true
     * who : daimajia
     */

    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }
}
