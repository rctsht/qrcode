#import "RCTView.h"

@interface RSQrCode : RCTView

  @property (nonatomic, strong, readonly) NSData *data;
  @property (nonatomic, assign) NSString *errorCorrectionLevel;
  @property (nonatomic, assign) CGSize size;
  @property (nonatomic, strong) CIColor *color;

  - (UIImage *)generateQrCode;

  -(void)setColor:(CIColor *)color;
  -(void)setData:(NSData *)data;
  -(void)setErrorCorrectionLevel:(NSString *)errorCorrectionLevel;

@end
