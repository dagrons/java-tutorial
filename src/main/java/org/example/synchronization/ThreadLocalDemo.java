package org.example.synchronization;


import java.util.HashMap;
import java.util.Map;

public class ThreadLocalDemo {

    class Processor {
        Map<String, String> params = new HashMap<>();
        private static ThreadLocal<Long> logid = new ThreadLocal<Long>();

        public Processor(Map<String, String> params) {
            this.params = params;
        }

        public Processor() {

        }

        public void preprocess() {
            if (params.containsKey("logid")) {
                logid.set(Long.valueOf(params.get("logid")));
            }
        }

        public void process() {
            preprocess();
            System.out.println(logid.get());
        }
    }

    public static void main(String[] args) {
        ThreadLocalDemo threadLocalDemo = new ThreadLocalDemo();
        Map<String, String> params = new HashMap<>();
        params.put("logid", "12312341241");
        Processor processor = threadLocalDemo.new Processor(params);
        processor.process();
        Processor processor1 = threadLocalDemo.new Processor();
        processor1.process();
    }
}

