package com.base;

import org.apache.commons.collections4.map.ListOrderedMap;

import com.common.utils.CamelUtil;

@SuppressWarnings("rawtypes")
public class SqlMap extends ListOrderedMap {

	private static final long serialVersionUID = 4109959925683795177L;

	@SuppressWarnings("unchecked")
	@Override
    public Object put(Object key, Object value) {
        return super.put(CamelUtil.convert2CamelCase((String) key), value);
    }

}
