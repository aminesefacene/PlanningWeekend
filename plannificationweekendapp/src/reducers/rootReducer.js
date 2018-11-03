
export default function rootReducer(state = 
	{ user: undefined, activityList: [], regionList: [], availableActivities: [], availableRegions: [] }, action){
	switch(action.type){
		case 'CONNECT_USER':
			if(action.user!==undefined){
				return { ...state, user: action.user }
			}else{
				return { ...state, user: action.user, activityList: [], regionList: [] }
			}
		case 'GET_USER_ACTIVITIES':
			return { ...state, activityList: action.activityList }
		case 'GET_USER_REGIONS':
			return { ...state, regionList: action.regionList }
		case 'GET_ALL_ACTIVITIES':
			return { ...state, availableActivities: action.availableActivities }
		case 'GET_ALL_REGIONS':
			return { ...state, availableRegions: action.availableRegions }
		default:
			return state
    }
}