package com.ibm.spss.boot.configuration.datasource;

import javax.sql.DataSource;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.ibm.spss.boot.util.RunningProfile;

//@Configuration
//@Profile(RunningProfile.Production)
public class DataSourceConfiguration {

	public DataSource getDatasource(){
	
		return null;
	}
}
