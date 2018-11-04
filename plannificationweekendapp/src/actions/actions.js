export const getId = (i) => {
    return { type: 'GET_ID', id: i}
}

export const connectUser = (u) => {
    return { type: 'CONNECT_USER', user: u}
}

export const getUserActivities = (userActivities) => {
    return { type: 'GET_USER_ACTIVITIES', activityList: userActivities}
}

export const getUserRegions = (userRegions) => {
    return { type: 'GET_USER_REGIONS', regionList: userRegions}
}

export const getAllActivities = (AllActivities) => {
    return { type: 'GET_ALL_ACTIVITIES', availableActivities: AllActivities}
}

export const getAllRegions = (AllRegions) => {
    return { type: 'GET_ALL_REGIONS', availableRegions: AllRegions}
}
