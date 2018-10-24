import React from 'react';
import { connect } from "react-redux";

const mapStateToProps = (state) => {
    return {
      userActivities: state.activityList,
      allActivities: state.availableActivities
    }
}

class ActivityList extends React.Component {

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

    render() {
        return <div>
            <h3>Liste des activitées</h3>
            <ul>{this.displayUserActivities()}</ul>
            <select>{this.displayAllActivities()}</select>
            </div>
    }
}

const ConnectedActivityList = connect(mapStateToProps)(ActivityList);

export {
    ConnectedActivityList
}