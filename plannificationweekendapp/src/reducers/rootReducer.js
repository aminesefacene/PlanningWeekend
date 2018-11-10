export default function rootReducer(state = 
	{ id: -1, mailAddress: '', user: undefined, activityList: [], regionList: [], availableActivities: [], availableRegions: [] }, action){
	switch(action.type){
		case 'GET_ID':
		return { ...state, id: action.id }
		case 'CONNECT_USER':
			if(action.user!==undefined){
				return { ...state, user: action.user }
			}else{
				return { ...state, id: -1, mailAddress: '', user: action.user, activityList: [], regionList: [] }
			}
		case 'GET_USER_ACTIVITIES':
			return { ...state, activityList: action.activityList }
		case 'ADD_USER_ACTIVITY':
			return { ...state, activityList: [ ...state.activityList, action.newActivity] }
		case 'ADD_USER_REGION':
			return { ...state, regionList: [ ...state.regionList, action.newRegion] }
		case 'GET_USER_REGIONS':
			return { ...state, regionList: action.regionList }
		case 'GET_ALL_ACTIVITIES':
			return { ...state, availableActivities: action.availableActivities }
		case 'GET_ALL_REGIONS':
			return { ...state, availableRegions: action.availableRegions }
		case 'GET_USER_MAILADDRESS':
			return { ...state, mailAddress: action.mailAddress }
		default:
			return state
    }
}