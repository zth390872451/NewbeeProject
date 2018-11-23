package mybatis.plus.generator;

/**
 * 生成代码
 *
 * @author xie.m
 * @date 2018/8/7
 */
public class GeneratorCodeConfig {
	
	public static void main(String[] args) {
		String[] tables = new String[] { "role_permission" };
		GeneratorCodeUtil.generateByTables("zheng.th", "com.newbee.net", tables);
	}
}