app.controller('chartController', function($scope, $http) {
    var stations;


    var layoutVienna = {

        xaxis: {
            title: 'Longitude'
        },

        yaxis: {
            title: 'Latitude'

        },

        title:'Metro Stations Vienna',

        width: 600,
        height: 600
    };

    var layoutBudapest = {

        xaxis: {
            title: 'Longitude'
        },

        yaxis: {
            title: 'Latitude'

        },

        title:'Metro Stations Budapest',

        width: 600,
        height: 600
    };



    $http.get('http://localhost:8080/getStationsVienna').
    then(function(response) {
        stations = response.data;

        var trace1 = {
            x: [],
            y: [],
            mode: 'markers',
            type: 'scatter',
            text: []
        };


        for (var i = 0; i < stations.length; i++) {
            trace1.x.push(stations[i].longitude);
            trace1.y.push(stations[i].latitude);
            trace1.text.push(stations[i].name);
        }

        var data = [ trace1 ];

        Plotly.newPlot('viennaChart', data, layoutVienna);

    });

    $http.get('http://localhost:8080/getStationsBudapest').
    then(function(response) {
        stations = response.data;

        var trace1 = {
            x: [],
            y: [],
            mode: 'markers',
            type: 'scatter',
            text: []
        };


        for (var i = 0; i < stations.length; i++) {
            trace1.x.push(stations[i].longitude);
            trace1.y.push(stations[i].latitude);
            trace1.text.push(stations[i].name);
        }

        var data = [ trace1 ];

        Plotly.newPlot('budapestChart', data, layoutBudapest);

    });




});