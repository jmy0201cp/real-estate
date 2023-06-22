import React, { useEffect } from "react";

export default function RegionMap({ address }) {
  useEffect(() => {
    const { naver } = window;
    if (!naver) return;

    // 지도에 표시할 위치의 위도와 경도 좌표를 파라미터로 넣어줍니다.
    naver.maps.Service.geocode(
      {
        query: address,
      },
      function (status, response) {
        const error = naver.maps.Service.Status.ERROR;
        if (status === error) {
          return alert("지도를 로딩하는데 오류가 발생했습니다.");
        }

        if (response.v2.addresses === null || response.v2.addresses === "") {
          return alert("주소가 올바르지 않아, 지도가 열리지 않습니다.");
        }

        const x = Number(response.v2.addresses[0].x);
        const y = Number(response.v2.addresses[0].y);

        const location = new naver.maps.LatLng(y, x);
        const mapOptions = {
          center: new naver.maps.LatLng(y, x),
          zoom: 17,
          zoomControl: true,
          zoomControlOptions: {
            position: naver.maps.Position.TOP_RIGHT,
          },
          mapTypeControl: true, // 일반ㆍ위성 지도보기 컨트롤 표시 (기본값 표시안함)
        };

        const map = new naver.maps.Map("region", mapOptions);
        new naver.maps.Marker({
          position: location,
          map,
        });
      }
    );
  }, [address]);

  return <div id="region" className="border border-gray w-3/4 m-2 mr-3"></div>;
}
