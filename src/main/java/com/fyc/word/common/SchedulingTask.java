package com.fyc.word.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static com.fyc.word.common.Constant.LOCAL_DATA_FILE_PATH;

import java.io.File;

@Component
@Slf4j
public class SchedulingTask {

    @Scheduled(cron = "0 0 2 * * ?")
    public void deleteTempFile() {
        File file = new File(LOCAL_DATA_FILE_PATH);
        if (!file.exists()) {//判断是否待删除目录是否存在
            log.error("文件夹不存在");
        }

        String[] content = file.list();//取得当前目录下所有文件和文件夹
        for (String name : content) {
            File temp = new File(LOCAL_DATA_FILE_PATH, name);
            if (!temp.delete()) {
                log.error("删除文件" + name + "失败");
            }
        }
    }
}
