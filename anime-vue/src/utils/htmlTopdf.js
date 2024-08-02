/* eslint-disable */
// 页面导出为pdf格式,A4格式
import html2Canvas from 'html2canvas'
import jsPDF from 'jspdf'
import dayjs from 'dayjs'

const vh2coptions = {
  allowTaint: false,
  taintTest: false,
  logging: false,
  useCORS: true,
  dpi: window.devicePixelRatio * 1, //调整为适当的DPI，避免过高导致性能问题
  scale: 1, //按比例增加分辨率
  background: '#FFFFFF', //如果指定的div没有设置背景色会默认成黑色
}
const htmlTopdf = {
  getPdf(htmldom, filename,PdfFormat,PdfSize) {
    //默认a5大小，横向打印
    var a4Width = 190
    var a4Height = 128
    // var pdfstyle='a5'
    var pdfstyle=[210,148]
    if(PdfSize=='4'){
      //A4大小，210mm x 297mm，四边各保留10mm的边距，显示区域190x277
      if(PdfFormat=='0'){
        a4Width = 277
        a4Height = 190
        pdfstyle=[297,210]
      }else{
        a4Width = 190
        a4Height = 277
        pdfstyle='a4'
      }
    }else if(PdfSize=='5'){
      //A5大小，148mm x 210mm，四边各保留10mm的边距，显示区域128x190
      if(PdfFormat=='0'){
        a4Width = 190
        a4Height = 128
        pdfstyle=[210,148]
      }else{
        a4Width = 128
        a4Height = 190
        pdfstyle='a5'
      }
    }
    console.log(a4Width,a4Height,pdfstyle)
    /**
     * 方法有缺陷，分辨率过高、图片过长时渲染不及时会出现pdf黑屏，大约10页时，需要降低分辨率
     * 避免方法，降低分辨率
     */
    if(htmldom.scrollHeight>((htmldom.scrollWidth / a4Width) * a4Height*10)){
      vh2coptions.scale=1
    }else{
      vh2coptions.scale=4
    }
    console.log('分辨率',vh2coptions.scale)
    return new Promise((resolve, reject) => {
      try {
        html2Canvas(htmldom, vh2coptions).then(canvas => {
          //未生成pdf的html页面高度
          var canvasHeight = canvas.height
          //一页pdf显示html页面生成的canvas高度;
          var a4HeightRef = Math.floor((canvas.width / a4Width) * a4Height)
          //返回一个包含图片展示的 data URI
          var pageData = canvas.toDataURL('image/jpeg', 1.0)
          //方向：p（纵向），I（横向）,测量单位：mm，格式：a4
          var pdf = null
          if(PdfFormat=='0'){
            pdf = new jsPDF('I', 'mm', pdfstyle)
          }else{
            pdf = new jsPDF('p', 'mm', pdfstyle)
          }
          //pdf页面偏移
          var position = 0
          var index = 1,height,
              //创建一个由标签名称 tagName 指定的 HTML 元素
              canvas1 = document.createElement('canvas')
          pdf.setDisplayMode('fullwidth', 'continuous', 'FullScreen')
          //当内容未超过pdf一页显示的范围，无需分页
          if (canvasHeight < a4HeightRef) {
            pdf.addImage(pageData, 'JPEG', 10, 10, a4Width, (a4Width / canvas.width) * canvasHeight)
            pdf.save(filename + '.pdf')
            const file = new File([pdf.output("blob")], dayjs().format('YYYYMMDDHHmmss') + '.pdf', {});
            resolve(file)
          } else {
            try {
              pdf.deletePage(0)
              createImpl(canvas).then(res=>{
                resolve(res)
              })
              // setTimeout(createImpl, 500, canvas)
            } catch (err) {
            }
          }
          function createImpl(canvas) {
            return new Promise((resolve, reject) => {
              try {
                if (canvasHeight > 0) {
                  index++
                  var checkCount = 0
                  if (canvasHeight > a4HeightRef) {
                    var i = position + a4HeightRef
                    for (i = position + a4HeightRef; i >= position; i--) {
                      var isWrite = true
                      for (var j = 0; j < canvas.width; j++) {
                        var c = canvas.getContext('2d').getImageData(j, i, 1, 1).data
                        if (c[0] != 0xff || c[1] != 0xff || c[2] != 0xff) {
                          isWrite = false
                          break
                        }
                      }
                      if (isWrite) {
                        checkCount++
                        if (checkCount >= 10) {
                          break
                        }
                      } else {
                        checkCount = 0
                      }
                    }
                    height = Math.round(i - position) || Math.min(canvasHeight, a4HeightRef)
                    if (height <= 0) {
                      height = a4HeightRef
                    }
                  } else {
                    height = canvasHeight
                  }
                  canvas1.width = canvas.width
                  canvas1.height = height
                  var ctx = canvas1.getContext('2d')
                  ctx.drawImage(canvas, 0, position, canvas.width, height, 0, 0, canvas.width, height,)
                  if (position != 0) {
                    pdf.addPage()
                  }
                  var pageData = canvas1.toDataURL('image/jpeg', 1.0)
                  pdf.addImage(pageData, 'JPEG', 10, 10, a4Width, (a4Width / canvas1.width) * height)
                  canvasHeight -= height
                  position += height
                  if (canvasHeight > 0) {
                    createImpl(canvas).then(res=>{
                      resolve(res)
                    })
                    // setTimeout(createImpl, 500, canvas)
                  } else {
                    pdf.save(filename + '.pdf')
                    const file = new File([pdf.output("blob")], dayjs().format('YYYYMMDDHHmmss') + '.pdf', {});
                    console.log(file)
                    resolve(file)
                  }
                }
              }catch (err){
                reject(false)
              }
            })
          }
        })
      } catch (e) {
        reject(e)
      }
    })
  }
};
export default htmlTopdf;
