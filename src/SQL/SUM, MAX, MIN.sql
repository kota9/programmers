-- 가장 비싼 상품 구하기
문제 설명
다음은 어느 의류 쇼핑몰에서 판매 중인 상품들의 정보를 담은 PRODUCT 테이블입니다. PRODUCT 테이블은 아래와 같은 구조로 되어있으며, PRODUCT_ID, PRODUCT_CODE, PRICE는 각각 상품 ID, 상품코드, 판매가를 나타냅니다.

Column name	Type	Nullable
PRODUCT_ID	INTEGER	FALSE
PRODUCT_CODE	VARCHAR(8)	FALSE
PRICE	INTEGER	FALSE
상품 별로 중복되지 않는 8자리 상품코드 값을 가지며, 앞 2자리는 카테고리 코드를 의미합니다.

문제
PRODUCT 테이블에서 판매 중인 상품 중 가장 높은 판매가를 출력하는 SQL문을 작성해주세요. 이때 컬럼명은 MAX_PRICE로 지정해주세요.

예시
예를 들어 PRODUCT 테이블이 다음과 같다면

PRODUCT_ID	PRODUCT_CODE	PRICE
1	A1000001	10000
2	A2000005	9000
3	C1000006	22000
가장 높은 판매가는 22,000 원 이므로, 다음과 같은 결과가 나와야 합니다.

MAX_PRICE
22000

-- 답안
SELECT
    MAX(price) AS max_price
FROM PRODUCT


-- 가격이 제일 비싼 식품의 정보 출력하기
문제 설명
다음은 식품의 정보를 담은 FOOD_PRODUCT 테이블입니다. FOOD_PRODUCT 테이블은 다음과 같으며 PRODUCT_ID, PRODUCT_NAME, PRODUCT_CD, CATEGORY, PRICE는 식품 ID, 식품 이름, 식품 코드, 식품분류, 식품 가격을 의미합니다.

Column name	Type	Nullable
PRODUCT_ID	VARCHAR(10)	FALSE
PRODUCT_NAME	VARCHAR(50)	FALSE
PRODUCT_CD	VARCHAR(10)	TRUE
CATEGORY	VARCHAR(10)	TRUE
PRICE	NUMBER	TRUE
문제
FOOD_PRODUCT 테이블에서 가격이 제일 비싼 식품의 식품 ID, 식품 이름, 식품 코드, 식품분류, 식품 가격을 조회하는 SQL문을 작성해주세요.

예시
FOOD_PRODUCT 테이블이 다음과 같을 때

PRODUCT_ID	PRODUCT_NAME	PRODUCT_CD	CATEGORY	PRICE
P0018	맛있는고추기름	CD_OL00008	식용유	6100
P0019	맛있는카놀라유	CD_OL00009	식용유	5100
P0020	맛있는산초유	CD_OL00010	식용유	6500
P0021	맛있는케첩	CD_OL00001	소스	4500
P0022	맛있는마요네즈	CD_OL00002	소스	4700
SQL을 실행하면 다음과 같이 출력되어야 합니다.

PRODUCT_ID	PRODUCT_NAME	PRODUCT_CD	CATEGORY	PRICE
P0020	맛있는산초유	CD_OL00010	식용유	6500

-- 답안
SELECT
    *
FROM food_product
ORDER BY price desc
LIMIT 1

