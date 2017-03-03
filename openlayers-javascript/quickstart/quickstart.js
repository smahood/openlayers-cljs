var qs = {};

qs.source = new ol.source.OSM();

qs.tile = new ol.layer.Tile({source: qs.source});

qs.layers = [qs.tile];

qs.center = ol.proj.fromLonLat([37.41, 8.82]);

qs.view = new ol.View({center: qs.center, zoom: 4});


console.log(qs.center);

console.log(qs.source);



qs.map = new ol.Map({
        target: 'map',
        layers: qs.layers,
        view: qs.view
        });
