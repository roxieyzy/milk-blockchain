package com.example.food.common;

import com.example.food.dao.TraceDao;
import com.example.food.entity.Trace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;

@Service
public class Fabric {
    private String http = "http://localhost:8081/sdk";
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private TraceDao traceDao;

//数据上链
    public void sdk(Trace trace1,int i){
        String name = "";

            Trace trace = traceDao.queryByOnlyKey(trace1);
        if(i == 2){
            name = "生产商 : "+trace.getPname();
        }else if(i == 3){
            name = "运输商 : "+trace.getTname();
        }else if(i == 4){
            name = "销售商 : "+trace.getSname();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
             String s = "\"pname\":\"" + trace.getPname()
                    + "\",\"paddress\":\"" + trace.getPaddress()
                    + "\",\"ptime\":\"" + (trace.getPtime()==null?"null":sdf.format(trace.getPtime()))
                    + "\",\"pperson\":\"" + trace.getPperson()
                    + "\",\"tname\":\"" + trace.getTname()
                    + "\",\"tperson\":\"" + trace.getTperson()
                    + "\",\"tbeginaddress\":\"" + trace.getTbeginaddress()
                    + "\",\"tarriveaddress\":\"" + trace.getTarriveaddress()
                    + "\",\"sname\":\"" + trace.getSname()
                    + "\",\"sperson\":\"" + trace.getSperson()
                    + "\",\"stime\":\"" + (trace.getStime()==null?"null":sdf.format(trace.getStime()))
                    + "\",\"saddress\":\"" + trace.getSaddress()
                    + "\",\"role\":\"" + name + "\"";
            String URL_invoke = http + "/invoke?id=" + trace.getOnlykey() + "&data=" + s;
            ResponseEntity<String> forEntity = restTemplate.getForEntity(URL_invoke, String.class);
    }

}
