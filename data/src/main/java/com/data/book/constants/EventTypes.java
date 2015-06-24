package com.data.book.constants;

import org.springframework.util.StringUtils;

public enum EventTypes {
		SECURITY_API,
		PROVISION,
		INVALID;
		
		public static EventTypes getType(String type){
			if(StringUtils.isEmpty(type)){
				return EventTypes.INVALID;
			}
			for(EventTypes value: EventTypes.values()){
				if(type.equals(value.name())){
					return value;
				}
			}
			return EventTypes.INVALID;
		}
}
