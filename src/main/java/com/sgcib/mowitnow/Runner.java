package com.sgcib.mowitnow;

import com.sgcib.mowitnow.model.GardenGround;
import com.sgcib.mowitnow.service.FileReader;
import com.sgcib.mowitnow.service.MowerFileReader;
import com.sgcib.mowitnow.service.MowerService;
import com.sgcib.mowitnow.service.MowerServiceImpl;
import lombok.extern.apachecommons.CommonsLog;

import java.util.List;

@CommonsLog
public class Runner {

    public static void main(String[] args) {
        FileReader reader = MowerFileReader.getInstance();
        List<String> fileContent = reader.parse(args[0]);
        MowerService service = MowerServiceImpl.getInstance();
        GardenGround ground = service.process(fileContent);
        log.info(ground);
    }
}
