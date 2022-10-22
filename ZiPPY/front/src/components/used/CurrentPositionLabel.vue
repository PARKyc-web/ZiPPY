<template>
  <div class="map_wrap">
    <div id="map"></div>
    <div class="hAddr">
      <span id="centerAddr"></span>
    </div>
  </div>
</template>

<script>
  export default {
    data: () => ({
      map: null,
      markers: [],
      latitude: 0,
      longitude: 0,
    }),
    created() {
      if (!("geolocation" in navigator)) {
        return;
      }

      // get position
      navigator.geolocation.getCurrentPosition(pos => {
        this.latitude = pos.coords.latitude;
        this.longitude = pos.coords.longitude;

        if (window.kakao && window.kakao.maps) {

          this.initMap();

        } else {
          const script = document.createElement("script");
          /* global kakao */
          script.onload = () => kakao.maps.load(this.initMap);
          document.head.appendChild(script);
        }

      }, err => {
        alert(err.message);
      })
    },
    methods: {
      initMap() {

        const container = document.getElementById("map");
        const options = {
          center: new kakao.maps.LatLng(33.450701, 126.570667),
          level: 5,
        };
        this.map = new kakao.maps.Map(container, options);
        this.displayMarker([
          [this.latitude, this.longitude]
        ]);
      },

      displayMarker(markerPositions) {
        if (this.markers.length > 0) {
          this.markers.forEach((marker) => marker.setMap(null));
        }

        const positions = markerPositions.map(
          (position) => new kakao.maps.LatLng(...position)
        );

        if (positions.length > 0) {
          this.markers = positions.map(
            (position) =>
            new kakao.maps.Marker({
              map: this.map,
              position,
            })
          );

          const bounds = positions.reduce(
            (bounds, latlng) => bounds.extend(latlng),
            new kakao.maps.LatLngBounds()
          );

          this.map.setBounds(bounds);

          // 주소-좌표 변환 객체를 생성합니다
          var geocoder = new kakao.maps.services.Geocoder();

          // 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
          searchAddrFromCoords(this.map.getCenter(), displayCenterInfo);

          function searchAddrFromCoords(coords, callback) {
            // 좌표로 행정동 주소 정보를 요청합니다
            geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);
          }

          var out = this;
          // 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
          function displayCenterInfo(result, status) {
            if (status === kakao.maps.services.Status.OK) {
              var infoDiv = document.getElementById('centerAddr');

              for (var i = 0; i < result.length; i++) {
                // 행정동의 region_type 값은 'H' 이므로
                if (result[i].region_type === 'H') {
                  infoDiv.innerHTML = result[i].address_name;

                  // 대구광역시 남구 남산동 -> 대구광역시 남구
                  let sigudong = infoDiv.innerHTML.split(' ');
                  let sigu = sigudong[0] + ' ' + sigudong[1];
                  out.$emit('sigu',  sigu);
                  break;
                }
              }
            }
          }
        }
      }
    }
  }
</script>

<style scoped>
  .map_wrap {
    position: relative;
    width: 70%;
    height: fit-content;
  }

  /* 주소 폰트는 여기서 조절하시면 됩니다. */
  .hAddr {
    position: absolute;
    left: 10px;
    top: 10px;
    border-radius: 2px;
    z-index: 1;
  }

  #centerAddr {
    display: block;
    margin-top: 2px;
    font-weight: normal;
  }

  .bAddr {
    padding: 5px;
    text-overflow: ellipsis;
    overflow: hidden;
    white-space: nowrap;
  }
</style>