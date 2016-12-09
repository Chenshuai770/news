package com.cs.news1.entry;

import java.util.List;

/**
 * Created by chenshuai on 2016/11/28.
 */

public class Keshi {


    /**
     * status : true
     * tngou : [{"department":0,"departments":[{"department":1,"description":"","id":2,"keywords":"","name":"呼吸内科","seq":0,"title":""},{"department":1,"description":"","id":3,"keywords":"","name":"消化内科","seq":0,"title":""},{"department":1,"description":"","id":4,"keywords":"","name":"神经内科","seq":0,"title":""},{"department":1,"description":"","id":5,"keywords":"","name":"内分泌科","seq":0,"title":""},{"department":1,"description":"","id":6,"keywords":"","name":"肾内科","seq":0,"title":""},{"department":1,"description":"","id":7,"keywords":"","name":"风湿科","seq":0,"title":""},{"department":1,"description":"","id":8,"keywords":"","name":"血液科","seq":0,"title":""},{"department":1,"description":"","id":9,"keywords":"","name":"心血管内科","seq":0,"title":""}],"description":"","id":1,"keywords":"","name":"内科","seq":0,"title":""},{"department":0,"departments":[{"department":10,"description":"","id":11,"keywords":"","name":"普外科","seq":0,"title":""},{"department":10,"description":"","id":12,"keywords":"","name":"心胸外科","seq":0,"title":""},{"department":10,"description":"","id":13,"keywords":"","name":"肝胆外科","seq":0,"title":""},{"department":10,"description":"","id":14,"keywords":"","name":"胃肠外科","seq":0,"title":""},{"department":10,"description":"","id":15,"keywords":"","name":"脑外科","seq":0,"title":""},{"department":10,"description":"","id":16,"keywords":"","name":"泌尿外科","seq":0,"title":""},{"department":10,"description":"","id":17,"keywords":"","name":"骨科","seq":0,"title":""},{"department":10,"description":"","id":18,"keywords":"","name":"肛肠外科","seq":0,"title":""},{"department":10,"description":"","id":19,"keywords":"","name":"乳腺外科","seq":0,"title":""},{"department":10,"description":"","id":20,"keywords":"","name":"血管外科","seq":0,"title":""},{"department":10,"description":"","id":21,"keywords":"","name":"器官移植","seq":0,"title":""},{"department":10,"description":"","id":22,"keywords":"","name":"烧伤科","seq":0,"title":""},{"department":10,"description":"","id":24,"keywords":"","name":"外伤科","seq":0,"title":""}],"description":"","id":10,"keywords":"","name":"外科","seq":0,"title":""},{"department":0,"departments":[],"description":"","id":23,"keywords":"","name":"手外科","seq":0,"title":""},{"department":0,"departments":[{"department":25,"description":"","id":26,"keywords":"","name":"妇科","seq":0,"title":""},{"department":25,"description":"","id":27,"keywords":"","name":"产科","seq":0,"title":""}],"description":"","id":25,"keywords":"","name":"妇产科","seq":0,"title":""},{"department":0,"departments":[{"department":28,"description":"","id":29,"keywords":"","name":"眼科","seq":0,"title":""},{"department":28,"description":"","id":30,"keywords":"","name":"耳鼻喉科","seq":0,"title":""},{"department":28,"description":"","id":31,"keywords":"","name":"口腔科","seq":0,"title":""}],"description":"","id":28,"keywords":"","name":"五官科","seq":0,"title":""},{"department":0,"departments":[{"department":32,"description":"","id":33,"keywords":"","name":"皮肤科","seq":0,"title":""},{"department":32,"description":"","id":34,"keywords":"","name":"性病科","seq":0,"title":""}],"description":"","id":32,"keywords":"","name":"皮肤性病","seq":0,"title":""},{"department":0,"departments":[{"department":35,"description":"","id":36,"keywords":"","name":"中医科","seq":0,"title":""}],"description":"","id":35,"keywords":"","name":"中西医结合科","seq":0,"title":""},{"department":0,"departments":[{"department":37,"description":"","id":38,"keywords":"","name":"肝炎","seq":0,"title":""}],"description":"","id":37,"keywords":"","name":"肝病","seq":0,"title":""},{"department":0,"departments":[{"department":39,"description":"","id":40,"keywords":"","name":"精神病科","seq":0,"title":""},{"department":39,"description":"","id":41,"keywords":"","name":"心理咨询","seq":0,"title":""}],"description":"","id":39,"keywords":"","name":"精神心理科","seq":0,"title":""},{"department":0,"departments":[{"department":42,"description":"","id":43,"keywords":"","name":"儿科","seq":0,"title":""}],"description":"","id":42,"keywords":"","name":"儿科","seq":0,"title":""},{"department":0,"departments":[{"department":44,"description":"","id":45,"keywords":"","name":"男科","seq":0,"title":""}],"description":"","id":44,"keywords":"","name":"男科","seq":0,"title":""},{"department":0,"departments":[{"department":46,"description":"","id":47,"keywords":"","name":"生殖健康","seq":0,"title":""}],"description":"","id":46,"keywords":"","name":"生殖健康","seq":0,"title":""},{"department":0,"departments":[{"department":48,"description":"","id":49,"keywords":"","name":"肿瘤科","seq":0,"title":""}],"description":"","id":48,"keywords":"","name":"肿瘤科","seq":0,"title":""},{"department":0,"departments":[{"department":50,"description":"","id":51,"keywords":"","name":"传染科","seq":0,"title":""}],"description":"","id":50,"keywords":"","name":"传染科","seq":0,"title":""},{"department":0,"departments":[{"department":52,"description":"","id":53,"keywords":"","name":"老年科","seq":0,"title":""}],"description":"","id":52,"keywords":"","name":"老年科","seq":0,"title":""},{"department":0,"departments":[{"department":54,"description":"","id":55,"keywords":"","name":"体检保健科","seq":0,"title":""}],"description":"","id":54,"keywords":"","name":"体检保健科","seq":0,"title":""},{"department":0,"departments":[{"department":56,"description":"","id":57,"keywords":"","name":"成瘾医学科","seq":0,"title":""}],"description":"","id":56,"keywords":"","name":"成瘾医学科","seq":0,"title":""},{"department":0,"departments":[{"department":58,"description":"","id":59,"keywords":"","name":"核医学科","seq":0,"title":""}],"description":"","id":58,"keywords":"","name":"核医学科","seq":0,"title":""},{"department":0,"departments":[{"department":60,"description":"","id":61,"keywords":"","name":"急诊科","seq":0,"title":""}],"description":"","id":60,"keywords":"","name":"急诊科","seq":0,"title":""},{"department":0,"departments":[{"department":62,"description":"","id":63,"keywords":"","name":"营养科","seq":0,"title":""}],"description":"","id":62,"keywords":"","name":"营养科","seq":0,"title":""}]
     */

    private boolean status;
    private List<TngouBean> tngou;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<TngouBean> getTngou() {
        return tngou;
    }

    public void setTngou(List<TngouBean> tngou) {
        this.tngou = tngou;
    }

    public static class TngouBean {
        /**
         * department : 0
         * departments : [{"department":1,"description":"","id":2,"keywords":"","name":"呼吸内科","seq":0,"title":""},{"department":1,"description":"","id":3,"keywords":"","name":"消化内科","seq":0,"title":""},{"department":1,"description":"","id":4,"keywords":"","name":"神经内科","seq":0,"title":""},{"department":1,"description":"","id":5,"keywords":"","name":"内分泌科","seq":0,"title":""},{"department":1,"description":"","id":6,"keywords":"","name":"肾内科","seq":0,"title":""},{"department":1,"description":"","id":7,"keywords":"","name":"风湿科","seq":0,"title":""},{"department":1,"description":"","id":8,"keywords":"","name":"血液科","seq":0,"title":""},{"department":1,"description":"","id":9,"keywords":"","name":"心血管内科","seq":0,"title":""}]
         * description :
         * id : 1
         * keywords :
         * name : 内科
         * seq : 0
         * title :
         */

        private int department;
        private String description;
        private int id;
        private String keywords;
        private String name;
        private int seq;
        private String title;
        private List<DepartmentsBean> departments;

        public int getDepartment() {
            return department;
        }

        public void setDepartment(int department) {
            this.department = department;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSeq() {
            return seq;
        }

        public void setSeq(int seq) {
            this.seq = seq;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<DepartmentsBean> getDepartments() {
            return departments;
        }

        public void setDepartments(List<DepartmentsBean> departments) {
            this.departments = departments;
        }

        public static class DepartmentsBean {
            /**
             * department : 1
             * description :
             * id : 2
             * keywords :
             * name : 呼吸内科
             * seq : 0
             * title :
             */

            private int department;
            private String description;
            private int id;
            private String keywords;
            private String name;
            private int seq;
            private String title;

            public int getDepartment() {
                return department;
            }

            public void setDepartment(int department) {
                this.department = department;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getKeywords() {
                return keywords;
            }

            public void setKeywords(String keywords) {
                this.keywords = keywords;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getSeq() {
                return seq;
            }

            public void setSeq(int seq) {
                this.seq = seq;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
