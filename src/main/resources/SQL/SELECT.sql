-- 평균 일일 대여 요금 구하기
문제 설명
다음은 어느 자동차 대여 회사에서 대여중인 자동차들의 정보를 담은 CAR_RENTAL_COMPANY_CAR 테이블입니다. CAR_RENTAL_COMPANY_CAR 테이블은 아래와 같은 구조로 되어있으며, CAR_ID, CAR_TYPE, DAILY_FEE, OPTIONS 는 각각 자동차 ID, 자동차 종류, 일일 대여 요금(원), 자동차 옵션 리스트를 나타냅니다.

Column name	Type	Nullable
CAR_ID	INTEGER	FALSE
CAR_TYPE	VARCHAR(255)	FALSE
DAILY_FEE	INTEGER	FALSE
OPTIONS	VARCHAR(255)	FALSE
자동차 종류는 '세단', 'SUV', '승합차', '트럭', '리무진' 이 있습니다. 자동차 옵션 리스트는 콤마(',')로 구분된 키워드 리스트(예: '열선시트', '스마트키', '주차감지센서')로 되어있으며, 키워드 종류는 '주차감지센서', '스마트키', '네비게이션', '통풍시트', '열선시트', '후방카메라', '가죽시트' 가 있습니다.

문제
CAR_RENTAL_COMPANY_CAR 테이블에서 자동차 종류가 'SUV'인 자동차들의 평균 일일 대여 요금을 출력하는 SQL문을 작성해주세요. 이때 평균 일일 대여 요금은 소수 첫 번째 자리에서 반올림하고, 컬럼명은 AVERAGE_FEE 로 지정해주세요.

-- 답안
SELECT
    ROUND(AVG(daily_fee), 0) AS average_fee
FROM car_rental_company_car
WHERE car_type='SUV'


-- 인기있는 아이스크림
문제 설명
FIRST_HALF 테이블은 아이스크림 가게의 상반기 주문 정보를 담은 테이블입니다.FIRST_HALF 테이블 구조는 다음과 같으며, SHIPMENT_ID, FLAVOR, TOTAL_ORDER는 각각 아이스크림 공장에서 아이스크림 가게까지의 출하 번호, 아이스크림 맛, 상반기 아이스크림 총주문량을 나타냅니다.

NAME	TYPE	NULLABLE
SHIPMENT_ID	INT(N)	FALSE
FLAVOR	VARCHAR(N)	FALSE
TOTAL_ORDER	INT(N)	FALSE
문제
상반기에 판매된 아이스크림의 맛을 총주문량을 기준으로 내림차순 정렬하고 총주문량이 같다면 출하 번호를 기준으로 오름차순 정렬하여 조회하는 SQL 문을 작성해주세요.

예시
예를 들어 FIRST_HALF 테이블이 다음과 같을 때

SHIPMENT_ID	FLAVOR	TOTAL_ORDER
101	chocolate	3200
102	vanilla	2800
103	mint_chocolate	1700
104	caramel	2600
105	white_chocolate	3100
106	peach	2450
107	watermelon	2150
108	mango	2900
109	strawberry	3100
110	melon	3150
111	orange	2900
112	pineapple	2900
상반기 아이스크림 맛을 총주문량을 기준으로 내림차순 정렬하고 총주문량이 같은 경우 출하 번호를 기준으로 오름차순 정렬하면 chocolate, melon, white_chocolate, strawberry, mango, orange, pineapple, vanilla, caramel, peach, watermelon, mint_chocolate 순서대로 조회되어야 합니다. 따라서 SQL문을 실행하면 다음과 같이 나와야 합니다.

FLAVOR
chocolate
melon
white_chocolate
strawberry
mango
orange
pineapple
vanilla
caramel
peach
watermelon
mint_chocolate

-- 답안
SELECT
    flavor
FROM first_half
ORDER BY total_order DESC, shipment_id

