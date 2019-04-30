<#import "../main.ftl" as main>

<@main.main>
    <div class="row">
        <form class="col s12" action="/issue" method="post" name="postDTO" enctype="multipart/form-data">
            <div class="card">
                <div class="card-content">
                    <span class="card-title"></span>
                    <div class="row">
                        <div class="input-field col m3 s12">
                            <input name="title" id="input_text" type="text" data-length="10">
                            <label for="input_text">Input text</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                                    <textarea name="body" id="textarea2" class="materialize-textarea"
                                              data-length="120"></textarea>
                            <label for="textarea2">Textarea</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="file-field input-field col s12">
                            <div class="btn">
                                <span>File</span>
                                <input name="imageFile" type="file">
                            </div>
                            <div class="file-path-wrapper">
                                <#--<input class="file-path validate" type="text">-->
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col s12">
                            <div id="map" style=" height: 300px;"></div>
                        </div>
                    </div>
                    <input name="lat" id="lat" type="text" hidden value="">
                    <input name="lng" id="lng" type="text" hidden value="   ">
                    <div class="row">
                        <div class="col s12 m3">
                            <button class="btn waves-effect waves-light" type="submit" name="action">Submit
                                <i class="material-icons right">send</i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <script>
        var marker;

        function initMap(userLatLng) {
            var map = new google.maps.Map(document.getElementById('map'), {
                zoom: 12,
                center: userLatLng
            });
            map.addListener('click', function (e) {
                placeMarkerAndPanTo(e.latLng, map);
            });

        }

        function placeMarkerAndPanTo(latLng, map) {
            if (marker && marker.setMap) {
                marker.setMap(null);
            }
            marker = new google.maps.Marker({
                position: latLng,
                map: map
            });
            document.getElementById("lat").value=marker.getPosition().lat();
            document.getElementById("lng").value=marker.getPosition().lng();
            // alert(marker.getPosition().lat());
            // alert(marker.getPosition().lng());
            marker.addListener('click', function () {
                map.setZoom(18);
                map.setCenter(marker.getPosition());
            });
            map.panTo(latLng);

        }

        function writeAddressName(latLng) {
            var geocoder = new google.maps.Geocoder();
            geocoder.geocode({
                    "location": latLng
                },
                function (results, status) {
                    if (status === google.maps.GeocoderStatus.OK)
                        document.getElementById("address").innerHTML = results[0].formatted_address;
                    else
                        document.getElementById("error").innerHTML += "Unable to retrieve your address" + "<br />";
                });
        }

        function geolocationSuccess(position) {
            var userLatLng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
            writeAddressName(userLatLng);
            initMap(userLatLng);
        }

        function geolocationError(positionError) {
            initMap(new google.maps.LatLng(55.8304307, 49.0660806));
        }

        function geolocateUser() {
            if (navigator.geolocation) {
                var positionOptions = {
                    enableHighAccuracy: true,
                    timeout: 10 * 1000
                };
                navigator.geolocation.getCurrentPosition(geolocationSuccess, geolocationError, positionOptions);
            } else
                document.getElementById("error").innerHTML += "Your browser doesn't support the Geolocation API";
        }

        window.onload = geolocateUser;
    </script>
    <script async defer
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD-Gg2z_RGw4Pdmiy82IEIVwBtBMpvtg14&language=ru&region=RU&sensor=true">
    </script>
</@main.main>