import React, { Component } from 'react';
import './App.css';
import { ConnectedConnectionForm } from './components/connectionForm';

class App extends Component {
  render() {
    return (
      <div className="App">
        <ConnectedConnectionForm/>
      </div>
    );
  }
}

export default App;
