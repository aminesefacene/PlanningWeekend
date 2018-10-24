import React from 'react';
import { connect } from "react-redux";

const mapStateToProps = (state) => {
    return {
      userRegions: state.regionList,
      allRegions: state.availableRegions
    }
}

class RegionList extends React.Component {

    displayUserRegions = () => {
        var Data = this.props.userRegions, MakeItem = function(X) {
            return <li key={X.city}>{X.city}</li>//comment afficher la région ???
        };
        return Data.map(MakeItem);
    }

    displayAllRegions = () => {
        var Data = this.props.allRegions, MakeItem = function(X) {
              return <option key={X.city}>{X.city}</option>//comment afficher la région ???
        };
        return Data.map(MakeItem);
    }

    render() {
        return <div>
            <h3>Liste des régions</h3>
            <ul>{this.displayUserRegions()}</ul>
            <select>{this.displayAllRegions()}</select>
            </div>
    }
}

const ConnectedRegionList = connect(mapStateToProps)(RegionList);

export {
    ConnectedRegionList
}