//使用方法
//import {getmark} from '@/utils/watermark'
//getmark().watermark(width,height,size,angle,str)
let id=''
let useWaterMark=true
export const getmark = () => {
    /**
     *
     * @param width 水印之间的高度
     * @param height 水印之间的宽度
     * @param size 文字大小
     * @param angle 旋转角度
     * @param str 水印文字
     * @returns {string}
     */
    const setWatermark = (width,height,size,angle,str) => {
        console.log('显现水印')
        id = "watermark";
        if (document.getElementById(id) !== null) {
            document.body.removeChild(document.getElementById(id));
        }
        //创建一个画布
        const can = document.createElement("canvas");
        //设置画布的长宽
        can.width = width||600;
        can.height = height||400;
        const cans = can.getContext("2d");
        //旋转角度
        cans.rotate(((angle) * Math.PI) / 180);
        cans.font = (size+' '+"Vedana")||"45px Vedana";
        //设置填充绘画的颜色、渐变或者模式
        cans.fillStyle = "rgba(200,200,200,0.4)";
        //设置文本内容的当前对齐方式
        cans.textAlign = "left";
        //设置在绘制文本时使用的当前文本基线
        // cans.textBaseline = "Middle";
        //在画布上绘制填色的文本（输出的文本，开始绘制文本的X坐标位置，开始绘制文本的Y坐标位置）
        cans.fillText(str, can.width / 8, can.height / 2);
        const div = document.createElement("div");
        div.id = id;
        div.style.pointerEvents = "none";
        div.style.top = "220px";
        div.style.left = "0px";
        div.style.position = "fixed";
        div.style.zIndex = 100;
        div.style.width = document.documentElement.clientWidth + "px";
        div.style.height = (document.documentElement.clientHeight - 400) + "px";
        div.style.background =
            "url(" + can.toDataURL("image/png") + ") left top repeat";
        document.body.appendChild(div);
        return id;
    };

    const clearmark=()=>{
        if(useWaterMark){
            //启用水印
            if(id&&document.getElementById(id) !== null){
                console.log('清除水印')
                document.body.removeChild(document.getElementById(id));
            }else if(id=='watermark'){
                if (document.getElementById(id) !== null) {
                    document.body.removeChild(document.getElementById(id));
                }
            }
        }else{
            return false
        }
    }

    // 该方法只允许调用一次
    const watermark = (width,height,size,angle,str) => {
        if(useWaterMark){
            //启用水印
            id = setWatermark(width,height,size,angle,str);
        }else{
            return false
        }
    };
    return { watermark,clearmark };
};
