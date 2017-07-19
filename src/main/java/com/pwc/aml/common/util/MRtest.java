package com.pwc.aml.common.util;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


/**
 * Created by ochen027 on 7/17/2017.
 */
public class MRtest extends Configured implements Tool {


    public static class tb1map extends TableMapper<ImmutableBytesWritable, Put>{
        @Override
        protected void map(ImmutableBytesWritable key,Result value,Context context)
                throws IOException, InterruptedException {
            // TODO Auto-generated method stub
            //new the put instance
            //ImmutableBytesWritable 就是rowKey



            Put put = new Put(key.get());



            //过滤筛选info:name列
            for(Cell cell : value.rawCells()){
                //过滤列簇


                if("info".equals(Bytes.toString(CellUtil.cloneFamily(cell)))){
                    if("name".equals(Bytes.toString(CellUtil.cloneQualifier(cell)))){
                        put.add(cell);
                        context.write(key, put);
                    }
                }
            }
        }

    }


    /**
     * reduce
     * KEYIN, VALUEIN, KEYOUT, Mutation
     */
    public static class tb2reduce extends TableReducer<ImmutableBytesWritable, Put, ImmutableBytesWritable>{

        @Override
        protected void reduce(
                ImmutableBytesWritable key,
                Iterable<Put> values,Context context)
                throws IOException, InterruptedException {
            // TODO Auto-generated method stub
            //该写法仅适用于当前业务场景
            for( Put put : values){
                context.write(key, put);
            }
            //如果列簇不一样
            /**
             * 需求：值
             *         put:cell  ->  value
             * 思路：
             *         先把值从put中取出来，放入新的put
             */
            //Put newput = new Put(key.get());
            //for(Put put2 : values){
            //List<Cell> cells = put2.get(family, qualifier);
            //newput.add(newfamily, newqualifier, CellUtil.cloneValue(cell))
            //context.write(key, put);
            //}

        }

    }


    /**
     * driver
     */

    public int run(String[] args) throws Exception {
        // TODO Auto-generated method stub
        //get the conf instance
        Configuration conf = super.getConf();
        //create a job
        Job job = Job.getInstance(conf, "hbase-mr");
        job.setJarByClass(MRtest.class);

        //map and reduce
        Scan scan = new Scan();
        TableMapReduceUtil.initTableMapperJob(
                "nstest:tb1",        // input table
                scan,               // Scan instance to control CF and attribute selection
                tb1map.class,     // mapper class
                ImmutableBytesWritable.class,         // mapper output key
                Put.class,  // mapper output value
                job);
        TableMapReduceUtil.initTableReducerJob(
                "nstest:tb2",        // output table
                tb2reduce.class,    // reducer class
                job);
        job.setNumReduceTasks(1);   // at least one, adjust as required

        //submit job
        boolean issucessed = job.waitForCompletion(true);

        return issucessed ? 0:1;
    }

    public static void main(String[] args) throws Exception {

        //create the hbase conf instance
        Configuration conf = HBaseConfiguration.create();
        //run job
        int status = ToolRunner.run(conf, new MRtest(), args);
        //exit
        System.exit(status);
    }

}

