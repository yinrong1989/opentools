package com.yinrong.tools.util;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.RandomAccess;

/**
 * @Project:vfinance-open-tool
 * @Description:
 * @Company:fujie
 * @Create:2015年8月31日 下午6:40:54
 * @Author:SongZhiQiang
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class EmptyUtil {

	public static boolean isEmpty(Object obj) {
		if (obj == null)
			return true;
		if (obj instanceof String) {
			if (!"".equals(obj.toString()))
				return false;
		} else if (obj instanceof StringBuffer) {
			return isEmpty(obj.toString());
		} else if (obj instanceof Map) {
			if (!isEmpty(((Map) obj).values()))
				return false;
		} else if (obj instanceof Enumeration) {
			Enumeration enumeration = (Enumeration) obj;
			while (enumeration.hasMoreElements()) {
				if (!isEmpty(enumeration.nextElement()))
					return false;
			}
		} else if (obj instanceof Iterable) {
			if (obj instanceof List && obj instanceof RandomAccess) {
				List<Object> objList = (List) obj;
				for (int i = 0; i < objList.size(); i++) {
					if (!isEmpty(objList.get(i)))
						return false;
				}

			} else if (!isEmpty(((Iterable) obj).iterator()))
				return false;
		} else if (obj instanceof Iterator) {
			Iterator it = (Iterator) obj;
			while (it.hasNext()) {
				if (!isEmpty(it.next()))
					return false;
			}
		} else if (obj instanceof Object[]) {
			Object[] objs = (Object[]) obj;
			for (Object elem : objs) {
				if (!isEmpty(elem))
					return false;
			}
		} else if (obj instanceof int[]) {
			for (Object elem : (int[]) obj) {
				if (!isEmpty(elem))
					return false;
			}
		} else {
			return false;
		}
		return true;
	}
}
