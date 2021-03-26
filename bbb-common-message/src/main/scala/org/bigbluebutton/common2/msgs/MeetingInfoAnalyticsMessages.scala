package org.bigbluebutton.common2.msgs

object MeetingInfoAnalyticsMessage { val NAME = "MeetingInfoAnalyticsMessage" }
case class MeetingInfoAnalyticsMessage(
    header: BbbCoreBaseHeader,
    body:   MeetingInfoAnalyticsMessageBody
) extends BbbCoreMsg
case class MeetingInfoAnalyticsMessageBody(meetingInfo: MeetingInfoAnalytics)

object MeetingInfoAnalytics {
  def apply(name: String, externalId: String, internalId: String, hasUserJoined: Boolean, isRecording: Boolean, webcam: Webcam,
            audio: Audio, screenshare: Screenshare, users: List[Participant], presentation: PresentationInfo,
            breakoutRooms: BreakoutRoom): MeetingInfoAnalytics =
    new MeetingInfoAnalytics(name, externalId, internalId, hasUserJoined, isRecording, webcam, audio, screenshare, users,
      presentation, breakoutRooms)
}

case class MeetingInfoAnalytics(
    name:          String,
    externalId:    String,
    internalId:    String,
    hasUserJoined: Boolean,
    isRecording:   Boolean,
    webcams:       Webcam,
    audio:         Audio,
    screenshare:   Screenshare,
    users:         List[Participant],
    presentation:  PresentationInfo,
    breakoutRoom:  BreakoutRoom
)

case class Webcam(total: Int, streams: WebcamStream)
case class WebcamStream(broadcasts: List[Broadcast], viewers: Set[String])
case class User(id: String, name: String)
case class Broadcast(id: String, user: User, startedOn: Long)

case class Audio(total: Int, listenOnly: ListenOnlyAudioUser, twoWay: TwoWayAudioUser, phone: PhoneAudioUser)
case class ListenOnlyAudioUser(total: Int, users: List[User])
case class TwoWayAudioUser(total: Int, users: List[User])
case class PhoneAudioUser(total: Int, users: List[User])

case class Screenshare(stream: ScreenshareStream)
case class ScreenshareStream(user: User, viewers: List[User])

case class Participant(id: String, name: String, role: String)
case class PresentationInfo(id: String, name: String)
case class BreakoutRoom(id: String, names: List[String])