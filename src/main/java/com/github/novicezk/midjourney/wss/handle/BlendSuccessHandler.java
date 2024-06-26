package com.github.novicezk.midjourney.wss.handle;

import com.github.novicezk.midjourney.enums.MessageType;
import com.github.novicezk.midjourney.enums.TaskAction;
import com.github.novicezk.midjourney.support.TaskCondition;
import com.github.novicezk.midjourney.util.ContentParseData;
import com.github.novicezk.midjourney.util.ConvertUtils;
import net.dv8tion.jda.api.utils.data.DataObject;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * blend消息处理. 完成(create): **<https://s.mj.run/JWu6jaL1D-8> <https://s.mj.run/QhfnQY-l68o> --v 5.1**
 * - <@1012983546824114217> (relaxed)
 */
@Component
public class BlendSuccessHandler extends MessageHandler {

  @Override
  public void handle(MessageType messageType, DataObject message) {
    String content = getMessageContent(message);
    ContentParseData parseData = ConvertUtils.parseContent(content);
    if (MessageType.CREATE.equals(messageType) && parseData != null && hasImage(message)) {
      // Zoom也会和Blend具有类似的content！
      TaskCondition condition =
          new TaskCondition()
              .setActionSet(Set.of(TaskAction.BLEND, TaskAction.ZOOM))
              .setFinalPromptEn(parseData.getPrompt());
      findAndFinishImageTask(condition, parseData.getPrompt(), message);
    }
  }
}
