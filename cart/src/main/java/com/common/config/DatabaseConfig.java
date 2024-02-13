package com.common.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.ClassUtils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@EnableTransactionManagement
@PropertySource("classpath:/application.yml")
@MapperScan(basePackages={"com.**.**.mapper"} , sqlSessionFactoryRef = "sqlSessionFactory")
@Configuration
public class DatabaseConfig {
	public static String setTypeAliasesPackage() {
		String typeAliasesPackage = "com.*.*.model";
		String DEFAULT_RESOURCE_PATTERN = "*.class";
		
		ResourcePatternResolver resolver = (ResourcePatternResolver) new PathMatchingResourcePatternResolver();
		MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(
				resolver);
		typeAliasesPackage = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
				+ ClassUtils.convertClassNameToResourcePath(typeAliasesPackage)
				+ "/" + DEFAULT_RESOURCE_PATTERN;
		try {
			List<String> result = new ArrayList<String>();
			Resource[] resources = resolver.getResources(typeAliasesPackage);
			if (resources != null && resources.length > 0) {
				MetadataReader metadataReader = null;
				for (Resource resource : resources) {
					if (resource.isReadable()) {
						metadataReader = metadataReaderFactory
								.getMetadataReader(resource);
						try {
							result.add(Class
									.forName(metadataReader.getClassMetadata().getClassName())
									.getPackage().getName());
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
					}
				}
			}
			if (result.size() > 0) {
				HashSet<String> h = new HashSet<String>(result);
				result.clear();
				result.addAll(h);
				typeAliasesPackage = String.join("," ,(String[]) result.toArray(new String[0]));
			} else {
				throw new RuntimeException(
						"mybatis typeAliasesPackage	   ,   typeAliasesPackage:" + typeAliasesPackage + "	  ");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
//System.out.println("typeAliasesPackage...................................." + typeAliasesPackage);
		return typeAliasesPackage;
	}

    @Bean
    @ConfigurationProperties(prefix="spring.datasource.hikari")
    public HikariConfig hikariConfig() {
        HikariConfig hi = new HikariConfig();
        hi.setConnectionTimeout(60000);
        hi.setIdleTimeout(10000);
        hi.setMaximumPoolSize(20);
        hi.setMinimumIdle(5);
        hi.setMaxLifetime(2000000);
//        hi.setConnectionTestQuery("SELECT 1");
//        hi.setTransactionIsolation("TRANSACTION_READ_UNCOMMITTED");
        hi.setPoolName("cmn-HikariPool");
        return hi; //new HikariConfig();
    }

    @Bean(name ="dataSource")
    public DataSource dataSource() {
        DataSource dataSource = new HikariDataSource(hikariConfig());
        return dataSource;
    }

//    @Bean
//    public org.apache.ibatis.session.Configuration configuration() {
//        org.apache.ibatis.session.Configuration config = new org.apache.ibatis.session.Configuration();
//        config.setCacheEnabled(false);
//        config.setUseGeneratedKeys(true);
//        config.setDefaultExecutorType(ExecutorType.REUSE);
//        config.setAggressiveLazyLoading(false);
//        config.setMapUnderscoreToCamelCase(true);
//        config.setCallSettersOnNulls(true);
//        config.setJdbcTypeForNull(JdbcType.NULL);
//
//        // if("Y".equals(debugLog)) {
//        // Mybatis에서 생성되는 SQL에 디버깅용 comment
//        // config.setDefaultScriptingLanguage(EnhanceMybatisLanguageDriver.class);
//        // }
//        return config;
//    }

    @Primary
    @Qualifier("sqlSessionFactoryAlias")
    @Bean(name ="sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource
            , ApplicationContext applicationContext) throws Exception {

        String typeAliasesPackage = setTypeAliasesPackage();

        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setTypeAliasesPackage(typeAliasesPackage);
//        sessionFactory.setConfiguration(configuration());
        //PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setConfigLocation(applicationContext.getResource("classpath:/mappers/config/config-mapper.xml"));
        sessionFactory.setMapperLocations(applicationContext.getResources("classpath:/mappers/app/**/*.xml"));
        return sessionFactory.getObject();
    }

    @Primary
    @Qualifier("sqlSessionTemplateAlias")
    @Bean(name ="sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
        final SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSessionTemplate;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(){
        DataSourceTransactionManager manager = new DataSourceTransactionManager(dataSource());
        return manager;
    }
}
