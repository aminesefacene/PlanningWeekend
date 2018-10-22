export default function rootReducer(state = { user: undefined }, action){
	switch(action.type){
		case 'CONNECT_USER':
			return { ...state, user: action.user }		
		default:
			return state
    }
}