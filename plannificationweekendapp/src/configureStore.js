import { createStore, applyMiddleware } from 'redux'
import { createLogger } from 'redux-logger' // This will log into our console information about redux actions & store
import thunk from 'redux-thunk' // This middleware allows us to use Promises in our actions (async in action)

import rootReducer from './reducers/rootReducer'

const middlewares = applyMiddleware(thunk, createLogger())

export default function configureStore(preloadedState = {}) { // preloadedState is useful for SSR, which we don't use
  const store = createStore(rootReducer, preloadedState, middlewares)

  return store
}