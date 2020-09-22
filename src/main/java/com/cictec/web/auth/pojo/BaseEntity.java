package com.cictec.web.auth.pojo;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * Supper class of VO/POJO.
 * 
 * @Project GJDD-DTD
 * @author Ryan(lqs.net@163.com)
 * @since 2013-3-5
 * @version 1.3:
 * 
 *          <pre>
 *          [2013-03-05 by Ryan] init class with flyweight design pattern.
 *          [2013-03-15 by Ryan] apply chain-constructor design pattern.
 *          [2013-03-20 by Ryan] rewrite toString() method.
 *          [2013-03-26 by Ryan] improve toString() implemetion.
 * </pre>
 */
public class BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = 2347464031150641919L;

    private String uuid;

    private int userSessionId;//用户Id
   

	public int getUserSessionId() {
		return userSessionId;
	}

	public void setUserSessionId(int userSessionId) {
		this.userSessionId = userSessionId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUuid() {
        return uuid;
    }

    public BaseEntity setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    /**
     * override toString() method.
     */
    @Override
    public String toString() {
        return this.toString(stringPair2Map("uuid", uuid));
    }

    /**
     * Parse string array to map.
     * 
     * @param args
     *            name-value pair of fields.
     * @return Map<String, String>
     */
    public Map<String, String> stringPair2Map(String... args) {
        if (args == null || !volidateParams(args)) {
            return null;
        }

        Map<String, String> strMap = new HashMap<String, String>();

        for (int i = 0; i < (args.length / 2); i++) {
            strMap.put(args[2 * i], args[2 * i + 1]);
        }

        return strMap;
    }

    /**
     * Overwrite toString() method with parameter: Map<String, String>.
     * 
     * @param strMap
     *            name-value map of fields.
     * @return
     */
    public String toString(Map<String, String> strMap) {
        if (strMap == null || strMap.size() < 1) {
            return "";
        }

        StringBuilder strBld = new StringBuilder();
        strBld.append("#").append(this.getClass().getSimpleName())
                .append("#[ ");

        for (Entry<String, String> key : strMap.entrySet()) {
            strBld.append(key.getKey()).append("=").append(key.getValue())
                    .append(", ");
        }
        strBld.deleteCharAt(strBld.length() - 2);
        strBld.append("]");

        return strBld.toString();
    }

    /**
     * validate parameters, should be name-value pair of fields.
     * 
     * @param args
     *            validate target.
     * @return <boolean> true: args are valid; false: args is/are invalid.
     */
    private boolean volidateParams(String... args) {
        if (args != null && args.length % 2 != 0) {
            return false;
        }
        return true;
    }

    // /**
    // * 组装父类toString()结果，供子类引用。
    // *
    // * @param superStr
    // * @return
    // */
    // public String assembleSuperToString(String superStr) {
    // if (superStr == null || !superStr.startsWith("#"))
    // return "";
    //
    // StringBuilder strBld = new StringBuilder();
    // strBld.append("super{").append(superStr).append("}\r\n<extends>\r\n");
    //
    // return strBld.toString();
    // }

    public static void main(String[] args) {
        BaseEntity be = new BaseEntity()
                .setUuid("aEvlkdad02BVDeyocjalk6rTNpsd");
        System.out.println(be);
    }
}
