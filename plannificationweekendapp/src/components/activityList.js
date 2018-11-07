import React from 'react';
import { connect } from "react-redux";

const mapStateToProps = (state) => {
    return {
      userActivities: state.activityList,
      allActivities: state.availableActivities
    }
}

class ActivityList extends React.Component {

    constructor(props) {
        super(props);
        this.state = { selectedActivity: '', selectedLevel : '' };
    }

    handleChangeSelectedActivity(event) {
        this.setState({selectedActivity: event.target.value});
    }

    handleChangeSelectedLevel(event) {
        this.setState({selectedLevel: event.target.value});
    }

    displayUserActivities = () => {
        var Data = this.props.userActivities, MakeItem = function(X) {
              return <li key={X.name}>{X.name}</li>
        };
        return Data.map(MakeItem);
    }

    displayAllActivities = () => {
        var Data = this.props.allActivities, MakeItem = function(X) {
              return <option key={X.name}>{X.name}</option>
        };
        return Data.map(MakeItem);
    }

    addActivityToList = () => {
        
    }

    render() {
        return <div>
            <h3>Liste des activitées</h3>
            <ul>{this.displayUserActivities()}</ul>
            <select onChange={this.handleChangeSelectedActivity.bind(this)} value={this.state.selectedActivity}>{this.displayAllActivities()}</select>
            <select onChange={this.handleChangeSelectedLevel.bind(this)} value={this.state.selectedLevel}><option>EASY</option><option>MEDIUM</option><option>HARD</option></select>
            <button onClick={this.addActivityToList.bind(this)}>+</button>
            </div>
    }
}

const ConnectedActivityList = connect(mapStateToProps)(ActivityList);

export {
    ConnectedActivityList
}