#import <React/RCTViewManager.h>
#import <React/RCTBridge.h>
#import <React/RCTEventDispatcher.h>
#import <React/RCTUtils.h>
#import <React/UIView+React.h>
#import <React/RCTUIManager.h>

#import "RSQrCode.h"
#import "RSQrCodeViewManager.h"

@implementation RSQrCodeViewManager

- (UIView *)view
{
  return [[RSQrCode alloc] init];
}

RCT_CUSTOM_VIEW_PROPERTY(color, id, RSQrCode)
{
  [view setColor:[CIColor colorWithCGColor:[RCTConvert UIColor:json].CGColor]];
}

RCT_CUSTOM_VIEW_PROPERTY(data, NSString*, RSQrCode)
{
  [view setData:[[RCTConvert NSString:json] dataUsingEncoding:NSUTF8StringEncoding]];
}

RCT_CUSTOM_VIEW_PROPERTY(errorCorrectionLevel, NSString*, RSQrCode)
{
  [view setErrorCorrectionLevel:json ? [RCTConvert NSString:json] : @"M"];
}

RCT_EXPORT_MODULE(RSQrCodeView)

@end
