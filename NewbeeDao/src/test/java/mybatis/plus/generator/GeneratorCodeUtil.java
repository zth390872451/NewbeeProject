package mybatis.plus.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成代码工具类
 *
 * @author xie.m
 * @date 2018/8/7
 */
public class GeneratorCodeUtil {

	private GeneratorCodeUtil() {
		super();
	}

	/**
	 * 接口是否加I前缀
	 */
	private static final boolean SERVICE_NAME_START_WITH_I = true;

	/**
	 * 是否将mapper.xml文件生成到resources目录下
	 * 当设置为true，生成到resources目录下，设置为false，默认生成到mapper/xml目录下
	 */
	private static final boolean IS_CHANGE_MAPPER_DIR = true;

	/**
	 * 基础生成目录
	 */
	private static String baseOutputDir = null;

	/**
	 * 默认生成目录
	 */
	private static String outputDir = null;

	/**
	 * mapper文件生成目录
	 */
	private static String mapperOutputDir = null;

	/**
	 * 数据库连接
	 */
	private static final String DB_URL = "jdbc:mysql://10.234.7.109:3306/hrms";

	/**
	 * 数据库名
	 */
	private static final String DB_NAME = "hrms";

	/**
	 * 数据库密码
	 */
	private static final String DB_PD = "123456";

	/**
	 * 数据库驱动
	 */
	private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";

	/**
	 * 生成代码
	 * 
	 * @param packageName
	 *            包路径
	 * @param tableNames
	 *            表集合，可变参数，可以添加多个表名
	 */
	public static void generateByTables(String packageName, String... tableNames) {
		generateByTables(null, packageName, tableNames);
	}

	/**
	 * 生成代码
	 * 
	 * @param author
	 *            生成代码的作者
	 * @param packageName
	 *            包路径
	 * @param tableNames
	 *            表集合，可变参数，可以添加多个表名
	 */
	public static void generateByTables(String author, String packageName, String... tableNames) {
		generateByTables(author, packageName, DB_URL, DB_NAME, DB_PD, tableNames);
	}

	/**
	 * 生成代码
	 * 
	 * @param author
	 *            生成代码的作者
	 * @param packageName
	 *            包路径
	 * @param dbUrl
	 *            数据库路径
	 * @param dbName
	 *            数据库名
	 * @param dbPassword
	 *            数据库密码
	 * @param tableNames
	 *            表集合，可变参数，可以添加多个表名
	 */
	public static void generateByTables(String author, String packageName, String dbUrl, String dbName,
			String dbPassword, String... tableNames) {
		GlobalConfig config = new GlobalConfig();
		DataSourceConfig dataSourceConfig = new DataSourceConfig();
		dataSourceConfig.setDbType(DbType.MYSQL).setUrl(dbUrl).setUsername(dbName).setPassword(dbPassword)
				.setDriverName(DRIVER_NAME);

		StrategyConfig strategyConfig = new StrategyConfig();
		strategyConfig.setCapitalMode(true).setEntityLombokModel(false).setDbColumnUnderline(true)
				.setNaming(NamingStrategy.underline_to_camel).setEntityBooleanColumnRemoveIsPrefix(true)
				.setRestControllerStyle(true).setControllerMappingHyphenStyle(true).setSkipView(true)
				.setInclude(tableNames);// 修改替换成你需要的表名，多个表名传数组

		config.setActiveRecord(false).setAuthor(author).setOutputDir(outputDir).setFileOverride(true).setOpen(false)
				.setEnableCache(false);
		if (!SERVICE_NAME_START_WITH_I) {
			config.setServiceName("%sService");
		}

		PackageConfig packageConfig = new PackageConfig().setParent(packageName).setController("controller")
				.setEntity("entity");

		AutoGenerator autoGenerator = new AutoGenerator().setGlobalConfig(config).setDataSource(dataSourceConfig)
				.setStrategy(strategyConfig).setPackageInfo(packageConfig);
		if (IS_CHANGE_MAPPER_DIR) {
			InjectionConfig injectionConfig = getMapperDir();
			TemplateConfig templateConfig = getTemplateConfig();
			autoGenerator.setCfg(injectionConfig);
			autoGenerator.setTemplate(templateConfig);
		}
		autoGenerator.execute();
	}

	static {
		String rootDir = System.getProperty("user.dir");
		if (null != rootDir && !rootDir.equals("")) {
			baseOutputDir = rootDir.replace("\\", "/") + "/src/main/";
			outputDir = baseOutputDir + "/java";
			mapperOutputDir = baseOutputDir + "/resources/mapper/";
		}
	}

	private static TemplateConfig getTemplateConfig() {
		TemplateConfig templateConfig = new TemplateConfig();
		templateConfig.setXml(null);
		return templateConfig;
	}

	/**
	 * 获取mapper.xml文件的生成目录
	 * 
	 * @return
	 */
	private static InjectionConfig getMapperDir() {
		InjectionConfig injectionConfig = new InjectionConfig() {
			@Override
			public void initMap() {
				return;
			}
		};
		List<FileOutConfig> fileOutConfigList = new ArrayList<>();
		FileOutConfig fileOutConfig = new FileOutConfig("/templates/mapper.xml.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return mapperOutputDir + tableInfo.getEntityName() + "Mapper.xml";
			}
		};
		fileOutConfigList.add(fileOutConfig);
		injectionConfig.setFileOutConfigList(fileOutConfigList);
		return injectionConfig;
	}

}