//WebViewMessage.js
const RN_API = {
	GET_VERSION: 'GET_VERSION'
	,GET_POSITION: 'GET_POSITION'
};

function WebViewMessage(type, sendData){
	var pObj = new Promise((resolve, reject) => {
		if (!window.ReactNativeWebView) {
			reject('ReactNativeWebView 객체가 없습니다.');
			return;
		}
		const reqId = Date.now(); // uuid로 구현해도 좋습니다.
		const TIMEOUT = 3000; // 3 * 1000 = 3s
		const timer = setTimeout(() => {
			/** android */
			document.removeEventListener('message', listener);
			/** ios */
			window.removeEventListener('message', listener);
			reject('TIMEOUT');
		}, TIMEOUT);
		const listener = (event) => {
			const dataRtn = JSON.parse(event.data);
			// 같은 id만 처리합니다.
			if (dataRtn.reqId === reqId) {
				clearTimeout(timer);
				/** android */
				document.removeEventListener('message', listener);
				/** ios */
				window.removeEventListener('message', listener);
				resolve(dataRtn);
			}
		};
		window.ReactNativeWebView.postMessage(
			JSON.stringify({
				type : type,
				transData : sendData,
				reqId : reqId
			})
		);
		/** android */
		document.addEventListener('message', listener);
		/** ios */
		window.addEventListener('message', listener);
	});
	return pObj;
}

async function getInfoFromRN(mode,sendData){
	const infoData = await WebViewMessage(mode,sendData).catch((err)=>{
		switch(err){
			case "TIMEOUT": {
				alert("No response within 3 seconds.");
				break;
			}
			default: {
				alert("Error.");
				break;
			}
		}
		return null;
	});
	return infoData;
}
